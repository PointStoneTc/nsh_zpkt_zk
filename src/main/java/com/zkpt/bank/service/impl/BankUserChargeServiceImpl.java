package com.zkpt.bank.service.impl;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.util.DateUtils;
import com.zkpt.bank.entity.BankPacketHeadEntity;
import com.zkpt.bank.entity.BankProtocalPackage;
import com.zkpt.bank.entity.BankRespState;
import com.zkpt.bank.entity.BankUserChargeReqEntity;
import com.zkpt.bank.entity.BankUserChargeRespEntity;
import com.zkpt.bank.service.IBankUserChargeService;
import com.zkpt.data.service.OperationServiceI;
import com.zkpt.gas.entity.GasProtocalPackage;
import com.zkpt.gas.entity.GasRespState;
import com.zkpt.middleware.entity.MyConstant;
import com.zkpt.middleware.service.IMwFactoryService;

@Service("bankUserChargeService")
@Transactional
public class BankUserChargeServiceImpl extends BankServiceImpl implements IBankUserChargeService {
    private final static Logger logger = LoggerFactory.getLogger(BankUserChargeServiceImpl.class);
    private final static int splitCount = 3;

    @Autowired
    private IMwFactoryService mwFactoryService;
    @Autowired
    private OperationServiceI operationService;

    @Override
    public boolean request(BankProtocalPackage bankProtocalPackage) throws Exception {
        GasProtocalPackage gasProtocalPackage = protocalTransToGas(bankProtocalPackage, transBody(bankProtocalPackage));
        bankProtocalPackage.setPacketBodyEntity(BankUserChargeReqEntity.packaging(bankProtocalPackage.getPacketBody()));

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
        BankUserChargeRespEntity bankUserChargeResp = new BankUserChargeRespEntity();
        BankProtocalPackage protocalPackage = new BankProtocalPackage(); // 实际返回银行的协议包(编码器用)
        BankPacketHeadEntity bankPacketHead = serverSessionKey.getPacketHead();

        bankUserChargeResp.setSelf(bankUserChargeResp);
        boolean respError = false;
        String errorMsg = "";

        // step1:----------------------组装response响应包----------------------------
        // 01.交易码
        bankUserChargeResp.setTransactionCode_s(((BankUserChargeReqEntity) serverSessionKey.getPacketBodyEntity()).getTransactionCode_s());

        // 02.响应码
        switch (GasRespState.getEnumByKey(gasProtocalPackage.getPacketHead().getRespondState_s())) {
        case SUCESS:// 成功的响应码
            bankUserChargeResp.setResponseCode_s(BankRespState.TRADE_SUCESS.getKey());
            break;
        case FAIL_DETAIL:// 查询失败（具体见返回内容）
            errorMsg = MyConstant.GC_0103_FAIL_MESSAGE_002;
            bankUserChargeResp.setResponseCode_s(BankRespState.OTHER_ERROR.getKey());
            respError = true;
            break;
        case ONLY_TODAY:// 冲帐失败只能冲当天帐
            errorMsg = MyConstant.GC_0103_FAIL_MESSAGE_001;
            bankUserChargeResp.setResponseCode_s(BankRespState.TRADE_FAIL.getKey());
            respError = true;
            break;
        case FORMAL_ERROR: // 数据格式错误
            errorMsg = MyConstant.GC_0101_FAIL_MESSAGE_003;
            bankUserChargeResp.setResponseCode_s(BankRespState.PACKET_ERROR.getKey());
            respError = true;
            break;
        default:
            break;
        }

        // 失败要做的事
        if (respError) {
            errorWrite(bankUserChargeResp.getResponseCode_s(), errorMsg, serverSessionKey, splitCount);
            return false;
        }

        // step2:----------------------协议写入---------------------------
        protocalPackage.setPacketHead(serverSessionKey.getPacketHead());
        protocalPackage.setPacketBody(bankUserChargeResp.reply());
        protocalPackage.setBankCommand(serverSessionKey.getBankCommand());
        protocalPackage.setServerSessionKey(serverSessionKey.getServerSessionKey());
        mwFactoryService.getServerSession(serverSessionKey).write(protocalPackage);

        // step3:----------------------持久化----------------------------
        operationService.addOperationCharge(serverSessionKey, gasProtocalPackage);

        return true;
    }

    @Override
    public String transBody(BankProtocalPackage packetBody) {
        String[] vals = packetBody.getPacketBody().split(MyConstant.SPLIT1);
        StringBuffer sb = new StringBuffer();
        sb.append(vals[1]).append(MyConstant.SPLIT); // 用户号
        sb.append(vals[2]).append(MyConstant.SPLIT); // 缴费流水
        sb.append(DateUtils.date2Str(DateUtils.yyyyMMdd)).append(MyConstant.SPLIT).append(MyConstant.SPLIT2); // 缴费日期
        return sb.toString();
    }
}
