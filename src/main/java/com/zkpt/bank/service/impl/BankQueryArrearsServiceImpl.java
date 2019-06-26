package com.zkpt.bank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.redis.service.IRedisService;
import com.common.util.ProtocolTxTUtil;
import com.zkpt.bank.entity.BankCostArrearageReqEntity;
import com.zkpt.bank.entity.BankCostArrearageRespDetailEntity;
import com.zkpt.bank.entity.BankCostArrearageRespEntity;
import com.zkpt.bank.entity.BankPacketHeadEntity;
import com.zkpt.bank.entity.BankProtocalPackage;
import com.zkpt.bank.entity.BankRespState;
import com.zkpt.bank.service.IBankQueryArrearsService;
import com.zkpt.data.entity.AuthorizationArea;
import com.zkpt.data.service.AuthorizationAreaServiceI;
import com.zkpt.data.service.OperationServiceI;
import com.zkpt.gas.entity.GasCostArrearageRespDetailEntity;
import com.zkpt.gas.entity.GasProtocalPackage;
import com.zkpt.gas.entity.GasRespState;
import com.zkpt.middleware.entity.MyConstant;


@Service("bankQueryArrearsService")
@Transactional
public class BankQueryArrearsServiceImpl extends BankServiceImpl implements IBankQueryArrearsService {
    private final static Logger logger = LoggerFactory.getLogger(BankQueryArrearsServiceImpl.class);
    private final static int splitCount = 24;

    @Autowired
    private IRedisService redisService;
    @Autowired
    private OperationServiceI operationService;
    @Autowired
    private AuthorizationAreaServiceI authorizationAreaService;

    @Override
    public boolean request(BankProtocalPackage bankProtocalPackage) throws Exception {
        // 判断是否是开通收费地区的用户
        List<AuthorizationArea> authorizationAreaList = authorizationAreaService.findAllEffect();
        boolean userIsInvalid = true;
        if (authorizationAreaList.size() > 0) {
            String userNo_s = bankProtocalPackage.getPacketBody().split(MyConstant.SPLIT1)[1].substring(0, 4);
            for (AuthorizationArea area : authorizationAreaList) {
                if (area.getUserCode().equals(userNo_s)) {
                    userIsInvalid = false;
                    break;
                }
            }
        }

        if (userIsInvalid) {
            errorWrite(BankRespState.USER_NOEXIST.getKey(), MyConstant.ERROR_COM_012, bankProtocalPackage, splitCount);
            return false;
        }

        GasProtocalPackage gasProtocalPackage = protocalTransToGas(bankProtocalPackage, transBody(bankProtocalPackage));
        bankProtocalPackage.setPacketBodyEntity(BankCostArrearageReqEntity.packaging(bankProtocalPackage.getPacketBody()));
        IoSession ioSession = mwFactoryService.createMWClient();
        if (ioSession == null) {
            errorWrite(BankRespState.OTHER_ERROR.getKey(), MyConstant.ERROR_COM_001, bankProtocalPackage, splitCount);
            return false;
        }

        ioSession.setAttribute("serverSessionKey", bankProtocalPackage);
        ioSession.write(gasProtocalPackage);

        return true;
    }

    @Override
    public boolean response(GasProtocalPackage gasProtocalPackage, BankProtocalPackage serverSessionKey) throws Exception {
        BankCostArrearageRespEntity bankCostArrearageResp = new BankCostArrearageRespEntity(); // 返回银行客户端的响应包
        BankProtocalPackage protocalPackage = new BankProtocalPackage(); // 实际返回银行的协议包(编码器用)
        BankPacketHeadEntity bankPacketHead = serverSessionKey.getPacketHead();

        bankCostArrearageResp.setSelf(bankCostArrearageResp);
        boolean respError = false;
        String errorMsg = "";

        // step1:----------------------组装response响应包----------------------------
        // 01.交易码
        bankCostArrearageResp.setTransactionCode_s(((BankCostArrearageReqEntity) serverSessionKey.getPacketBodyEntity()).getTransactionCode_s());
        bankCostArrearageResp.setTransactionCode(ProtocolTxTUtil.strToCharArray(bankCostArrearageResp.getTransactionCode_s()));

        // 02.响应码
        switch (GasRespState.getEnumByKey(gasProtocalPackage.getPacketHead().getRespondState_s())) {
            case SUCESS:
                bankCostArrearageResp.setResponseCode_s(BankRespState.TRADE_SUCESS.getKey()); // 成功的响应码
                break;
            case FAIL_DETAIL:
                bankCostArrearageResp.setResponseCode_s(BankRespState.OTHER_ERROR.getKey()); // 查询失败
                errorMsg = MyConstant.ERROR_COM_010;
                respError = true;
                break;
            case NOARREARS:
                if (gasProtocalPackage.getPacketBody().indexOf(MyConstant.GC_0101_FAIL_MESSAGE_002) != -1) { // 用户不存在
                    bankCostArrearageResp.setResponseCode_s(BankRespState.USER_NOEXIST.getKey());
                    errorMsg = MyConstant.GC_0101_FAIL_MESSAGE_002;
                }
                respError = true;
                break;
            case FAIL:
                if (gasProtocalPackage.getPacketBody().indexOf(MyConstant.GC_0101_FAIL_MESSAGE_001) != -1) { // 用户无欠费
                    bankCostArrearageResp.setResponseCode_s(BankRespState.NO_ARREARS.getKey());
                    errorMsg = MyConstant.GC_0101_FAIL_MESSAGE_001;
                }
                respError = true;
                break;
            case MOREARREARS:
                bankCostArrearageResp.setResponseCode_s(BankRespState.OTHER_ERROR.getKey()); // 欠费月份太多，需要到营业所进行查询缴费
                errorMsg = MyConstant.GC_0101_FAIL_MESSAGE_004;
                respError = true;
                break;
            default:
                break;
        }

        // 失败要做的事
        if (respError) {
            errorWrite(bankCostArrearageResp.getResponseCode_s(), errorMsg, serverSessionKey, splitCount);
            return false;
        }

        int idx = 0;
        String temp = gasProtocalPackage.getPacketBody();
        // 01.欠费总额
        idx = temp.indexOf(MyConstant.SPLIT);
        bankCostArrearageResp.setTotalArrears_s(Double.parseDouble(temp.substring(0, idx)));
        temp = temp.substring(++idx);

        // 02.欠费月数
        idx = temp.indexOf(MyConstant.SPLIT);
        int month = Integer.parseInt(temp.substring(0, idx));
        temp = temp.substring(++idx);

        // 03.协议类型：1： 有协议，0： 无协议
        bankCostArrearageResp.setProtocolType_s("0");

        // 04.用户性质:0： 居民 1：单位
        bankCostArrearageResp.setUserProperty_s("0");

        // 05. 明细次数
        bankCostArrearageResp.setDetailTimes_s(Integer.valueOf(month));

        double consumption = 0d, penalty = 0d, arrearsTotal = 0d, lastRead = 0d, reading = 0d;
        int startMonth = 0, endMonth = 0;
        String userName = "", userNo = "";

        String[] monthDetail = temp.split(MyConstant.SPLIT3);

        List<BankCostArrearageRespDetailEntity> list = new ArrayList<BankCostArrearageRespDetailEntity>();
        List<GasCostArrearageRespDetailEntity> arrearList = new ArrayList<GasCostArrearageRespDetailEntity>();

        for (int i = 0; i < monthDetail.length; i++) {
            BankCostArrearageRespDetailEntity bankCostArrearageRespDetail = new BankCostArrearageRespDetailEntity();
            GasCostArrearageRespDetailEntity gasCostArrearageRespDetail = new GasCostArrearageRespDetailEntity();
            String[] vals = monthDetail[i].split(MyConstant.SPLIT1);
            userNo = vals[0];
            userName = vals[1];
            gasCostArrearageRespDetail.setUserNo_s(userNo);
            gasCostArrearageRespDetail.setUserName_s(userName);
            gasCostArrearageRespDetail.setMonth_s(vals[2]);
            gasCostArrearageRespDetail.setPrevVal_s(vals[3]);
            gasCostArrearageRespDetail.setCurrVal_s(vals[4]);
            gasCostArrearageRespDetail.setAirVal_s(vals[5]);
            gasCostArrearageRespDetail.setAirCost_s(vals[6]);
            gasCostArrearageRespDetail.setPayAbleAirNum_s(vals[7]);
            gasCostArrearageRespDetail.setLateFee_s(vals[8]);
            gasCostArrearageRespDetail.setPayAbleAirCost_s(vals[9]);
            gasCostArrearageRespDetail.setAirVal1_s(vals[10]);
            gasCostArrearageRespDetail.setAirCost1_s(vals[11]);
            gasCostArrearageRespDetail.setAirVal2_s(vals[12]);
            gasCostArrearageRespDetail.setAirCost2_s(vals[13]);
            gasCostArrearageRespDetail.setAirVal3_s(vals[14]);
            gasCostArrearageRespDetail.setAirCost3_s(vals[15]);
            gasCostArrearageRespDetail.setAirVal4_s(vals[16]);
            gasCostArrearageRespDetail.setAirCost4_s(vals[17]);

            // 抄表时间
            bankCostArrearageRespDetail.setMeterReadingTime_s("");
            // 起码
            bankCostArrearageRespDetail.setStartCode_s(vals[3]);
            // 止码
            bankCostArrearageRespDetail.setStopCode_s(vals[4]);
            // 实用吨数
            bankCostArrearageRespDetail.setPracticalTonnage_s(Double.parseDouble(vals[5]));
            consumption += Double.parseDouble(vals[5]);
            // 违约金
            bankCostArrearageRespDetail.setPenalty_s(Double.parseDouble(vals[8]));
            penalty += Double.parseDouble(vals[8]);
            // 本月欠费总额
            bankCostArrearageRespDetail.setCurrentMonthArrearsTotal_s(Double.parseDouble(vals[9]));
            arrearsTotal += Double.parseDouble(vals[9]);

            // 缴费标志
            bankCostArrearageRespDetail.setPaymentSign_s("0");
            list.add(bankCostArrearageRespDetail);
            arrearList.add(gasCostArrearageRespDetail);

            // 最近的一个月
            if (i == 0) {
                startMonth = Integer.parseInt(vals[2]);
                lastRead = Integer.parseInt(vals[3]);
            }

            // 最后一个月
            if (month == i + 1) {
                endMonth = Integer.parseInt(vals[2]);
                reading = Integer.parseInt(vals[4]);
            }
        }

        // 06.用户编号
        bankCostArrearageResp.setUserNo_s(userNo);

        // 07.用户名称
        bankCostArrearageResp.setUserName_s(userName);

        // 08.用气量
        bankCostArrearageResp.setGasConsumption_s(Double.valueOf(consumption));

        // 09. 气费
        bankCostArrearageResp.setGasFee_s(Double.valueOf(arrearsTotal));

        // 10.违约金
        bankCostArrearageResp.setPenalty_s(Double.valueOf(penalty));

        // 11.开始月份
        bankCostArrearageResp.setStartMonth_s(String.valueOf(startMonth));

        // 12.终止月份
        bankCostArrearageResp.setEndMonth_s(String.valueOf(endMonth));

        // 13.上次读数
        bankCostArrearageResp.setLastRead_s(Double.valueOf(lastRead));

        // 14.本次读数
        bankCostArrearageResp.setReading_s(Double.valueOf(reading));

        // 15. 应交金额:欠费总额-现有节余
        bankCostArrearageResp.setPayableAmount_s(Double.valueOf(arrearsTotal));
        bankCostArrearageResp.setList(list);

        String reply = bankCostArrearageResp.reply();
        bankPacketHead.setDataLength_s(ProtocolTxTUtil.complementText(String.valueOf(reply.length()), '0', 4, true));

        // step2:----------------------协议写入---------------------------
        protocalPackage.setPacketHead(serverSessionKey.getPacketHead());
        protocalPackage.setPacketBody(reply);
        protocalPackage.setBankCommand(serverSessionKey.getBankCommand());
        protocalPackage.setServerSessionKey(serverSessionKey.getServerSessionKey());
        mwFactoryService.getServerSession(serverSessionKey).write(protocalPackage);

        // step3:----------------------持久化----------------------------
        operationService.addOperationQueryArrears(serverSessionKey, gasProtocalPackage, userNo, userName, arrearsTotal, arrearList);

        // step4:----------------------缓存化----------------------------
        cacheArrears(userNo, arrearList);
        return true;
    }

    @Override
    public String transBody(BankProtocalPackage packetBody) {
        return packetBody.getPacketBody().split(MyConstant.SPLIT1)[1] + MyConstant.SPLIT;
    }

    /**
     * 缓存redis
     * 
     * @param userNo
     * @param gasCostArrearageRespDetailList
     * @return
     */
    private boolean cacheArrears(String userNo, List<GasCostArrearageRespDetailEntity> gasCostArrearageRespDetailList) {
        List<String> val = new ArrayList<String>();

        for (GasCostArrearageRespDetailEntity item : gasCostArrearageRespDetailList) {
            StringBuffer sb = new StringBuffer();
            val.add(sb.append(item.getMonth_s()).append(MyConstant.SPLIT).append(item.getPayAbleAirCost_s()).toString());
        }

        redisService.set(MyConstant.REDIS_USER_ARREAR_PREFIX + userNo, val, MyConstant.REDIS_DEFAULT_TIMETOLIVE);
        return true;
    }
}
