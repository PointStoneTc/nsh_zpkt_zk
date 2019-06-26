package com.zkpt.bank.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.util.DateUtils;
import com.common.util.FtpUtil;
import com.common.util.ProtocolTxTUtil;
import com.zkpt.bank.entity.BankGeneralLedgerFileSourceEntity;
import com.zkpt.bank.entity.BankGeneralLedgerReqEntity;
import com.zkpt.bank.entity.BankGeneralLedgerRespEntity;
import com.zkpt.bank.entity.BankPacketHeadEntity;
import com.zkpt.bank.entity.BankProtocalPackage;
import com.zkpt.bank.entity.BankRespState;
import com.zkpt.bank.service.IBankGeneralLedgerService;
import com.zkpt.data.entity.GasUserBehavior;
import com.zkpt.data.service.GasUserBehaviorServiceI;
import com.zkpt.data.service.OperationServiceI;
import com.zkpt.gas.entity.GasProtocalPackage;
import com.zkpt.gas.entity.GasRespState;
import com.zkpt.middleware.entity.MyConstant;
import com.zkpt.middleware.service.IMwFactoryService;

@Service("bankGeneralLedgerService")
@Transactional
public class BankGeneralLedgerServiceImpl extends BankServiceImpl implements IBankGeneralLedgerService {
    private final static Logger logger = LoggerFactory.getLogger(BankGeneralLedgerServiceImpl.class);
    private final static int splitCount = 2;

    @Autowired
    private Environment env;

    @Autowired
    private FtpUtil ftpUtil;

    @Autowired
    private IMwFactoryService mwFactoryService;

    @Autowired
    private OperationServiceI operationService;

    @Autowired
    private GasUserBehaviorServiceI gasUserBehaviorService;

    @Override
    public boolean request(BankProtocalPackage bankProtocalPackage) throws Exception {
        GasProtocalPackage gasProtocalPackage = protocalTransToGas(bankProtocalPackage, transBody(bankProtocalPackage));
        bankProtocalPackage.setPacketBodyEntity(BankGeneralLedgerReqEntity.packaging(bankProtocalPackage.getPacketBody()));

        IoSession ioSession = mwFactoryService.createMWClient();
        if (ioSession == null) {
            errorWrite(BankRespState.OTHER_ERROR.getKey(), MyConstant.ERROR_COM_001, bankProtocalPackage, splitCount);
            return false;
        }

        // 1.上传总对账文件至天然气ftp服务器
        if (!transLedgerFileFormBankFtpTogGasFtp(gasProtocalPackage.getPacketBody().split(MyConstant.SPLIT1)[0])) {
            errorWrite(BankRespState.OTHER_ERROR.getKey(), MyConstant.ERROR_COM_002, bankProtocalPackage, splitCount);
            return false;
        }

        // 2.发送总对账请求
        ioSession.setAttribute("serverSessionKey", bankProtocalPackage);
        ioSession.write(gasProtocalPackage);
        return true;
    }

    @Override
    public boolean response(GasProtocalPackage gasProtocalPackage, BankProtocalPackage serverSessionKey) throws Exception {
        BankGeneralLedgerRespEntity bankGeneralLedgerResp = new BankGeneralLedgerRespEntity();
        BankProtocalPackage protocalPackage = new BankProtocalPackage(); // 实际返回银行的协议包(编码器用)
        BankPacketHeadEntity bankPacketHead = serverSessionKey.getPacketHead();

        bankGeneralLedgerResp.setSelf(bankGeneralLedgerResp);
        boolean respError = false;
        String errorMsg = "";

        // step1:----------------------组装response响应包----------------------------
        // 01.交易码
        bankGeneralLedgerResp.setTransactionCode_s(((BankGeneralLedgerReqEntity) serverSessionKey.getPacketBodyEntity()).getTransactionCode_s());
        bankGeneralLedgerResp.setTransactionCode(ProtocolTxTUtil.strToCharArray(bankGeneralLedgerResp.getTransactionCode_s()));

        // 02.响应码
        switch (GasRespState.getEnumByKey(gasProtocalPackage.getPacketHead().getRespondState_s())) {
            case SUCESS:// 成功的响应码
                bankGeneralLedgerResp.setResponseCode_s(BankRespState.TRADE_SUCESS.getKey());
                bankGeneralLedgerResp.setResponseCode(ProtocolTxTUtil.strToCharArray(BankRespState.TRADE_SUCESS.getKey()));
                break;
            case NO_LEDGER_FILE:// 冲帐失败只能冲当天帐
                bankGeneralLedgerResp.setResponseCode_s(BankRespState.TRADE_FAIL.getKey());
                errorMsg = MyConstant.GC_0109_FAIL_MESSAGE_001;
                respError = true;
                break;
            case LEDGER_FILE_FORMAL_ERROR: // 数据格式错误
                bankGeneralLedgerResp.setResponseCode_s(BankRespState.PACKET_ERROR.getKey());
                errorMsg = MyConstant.GC_0109_FAIL_MESSAGE_002;
                respError = true;
                break;
            default:
                errorMsg = MyConstant.GC_0109_FAIL_MESSAGE_003;
                bankGeneralLedgerResp.setResponseCode_s(BankRespState.OTHER_ERROR.getKey());
                respError = true;
                break;
        }

        // 失败要做的事
        if (respError) {
            errorWrite(bankGeneralLedgerResp.getResponseCode_s(), errorMsg, serverSessionKey, splitCount);
            return false;
        }

        // step2:----------------------协议写入---------------------------
        bankPacketHead.setDataLength_s(ProtocolTxTUtil.complementText(String.valueOf(bankGeneralLedgerResp.reply().length()), '0', 4, true));
        protocalPackage.setPacketHead(serverSessionKey.getPacketHead());
        protocalPackage.setPacketBody(bankGeneralLedgerResp.reply());
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
        sb.append(vals[1]).append(MyConstant.SPLIT); // 缴费日期
        return sb.toString();
    }

    /**
     * 从银行Ftp下载对账文件,向天然气Ftp上传对账文件
     * 
     * @param downLoadFileName
     * @return
     * @throws Exception
     */
    private boolean transLedgerFileFormBankFtpTogGasFtp(String downLoadFileName) throws Exception {
        StringBuffer sbTemp = new StringBuffer();
        // 查看是否有今日已经发起过对账操作，如果发起过，返回false
        sbTemp.append(env.getProperty("middleware.ledger.downloadPath")).append(env.getProperty("middleware.bank.newCode").substring(1))
                .append(env.getProperty("middleware.ledger.ledgerNameGas")).append(downLoadFileName).append(env.getProperty("middleware.ledger.defaultFileExtensionName"));
        if (new File(sbTemp.toString()).exists())
            return false;

        // 银行对账文件路径
        sbTemp.setLength(0);
        sbTemp.append(env.getProperty("middleware.bank.oldCode")).append(env.getProperty("middleware.ledger.ledgerNameBank")).append(downLoadFileName);
        String remoteFile = sbTemp.toString();

        // 本地缓存对账文件副本的路径
        sbTemp.setLength(0);
        sbTemp.append(env.getProperty("middleware.ledger.downloadPath")).append(DateUtils.getMillis()).append(env.getProperty("middleware.ledger.defaultFileExtensionName"));
        String localFile = sbTemp.toString();

        // 银行ftp登录信息
        String ip_bank = env.getProperty("middleware.server.ftp.ip");
        int port_bank = Integer.parseInt(env.getProperty("middleware.server.ftp.port"));
        String user_bank = env.getProperty("middleware.server.ftp.user");
        String pwd_bank = env.getProperty("middleware.server.ftp.pwd");
        // 天然气ftp登录信息
        String ip_gas = env.getProperty("middleware.client.ftp.ip");
        int port_gas = Integer.parseInt(env.getProperty("middleware.client.ftp.port"));
        String user_gas = env.getProperty("middleware.client.ftp.user");
        String pwd_gas = env.getProperty("middleware.client.ftp.pwd");
        FTPClient ftpBank = null, ftpGas = null;
        BufferedReader in = null;
        BufferedWriter out = null;

        try {
            // 1.连接银行端的ftp
            ftpBank = ftpUtil.getFTPClient(ip_bank, port_bank, user_bank, pwd_bank);
            ftpUtil.connect(ftpBank, ip_bank, port_bank, user_bank, pwd_bank);

            // 2.缓存对账文件
            if (!ftpUtil.downLoadByFtp(remoteFile, localFile, ftpBank))
                return false;

            // 3.读取对账文件组合成新的文件并缓存
            BankGeneralLedgerFileSourceEntity bankGeneralLedgerFileSource = new BankGeneralLedgerFileSourceEntity();
            in = new BufferedReader(new InputStreamReader(new FileInputStream(localFile), env.getProperty("middleware.ledger.charSetName"))); // #这里主要是涉及中文
            String tempString = null;
            int count = 0;
            double mount = 0;

            while ((tempString = in.readLine()) != null) { // 一次读入一行，直到读入null为文件结束
                String[] temp = tempString.trim().split(MyConstant.SPLIT4);
                bankGeneralLedgerFileSource.getDetail().add(temp);
                mount += Double.valueOf(temp[1]);
                count++;
            }

            bankGeneralLedgerFileSource.setPaymentCount(count);
            bankGeneralLedgerFileSource.setPaymentMount(new BigDecimal(mount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            // 4.查询数据库中用户昨日的查询信息（包含欠费查询及缴费信息）
            List<GasUserBehavior> userBehaviorlist = gasUserBehaviorService.findAllYesterdayStream();
            Map<String, GasUserBehavior> userBehaviorMap = new HashMap<String, GasUserBehavior>();
            for (GasUserBehavior gb : userBehaviorlist)
                userBehaviorMap.put(gb.getUserNo(), gb);

            // 5.连接天然气端的ftp
            ftpGas = ftpUtil.getFTPClient(ip_gas, port_gas, user_gas, pwd_gas);
            ftpUtil.connect(ftpGas, ip_gas, port_gas, user_gas, pwd_gas);

            // 6.上传总对账文件至天然气ftp服务器
            sbTemp.setLength(0);
            sbTemp.append(env.getProperty("middleware.ledger.downloadPath")).append(env.getProperty("middleware.bank.newCode").substring(1))
                    .append(env.getProperty("middleware.ledger.ledgerNameGas")).append(downLoadFileName).append(env.getProperty("middleware.ledger.defaultFileExtensionName"));
            File fileTarget = new File(sbTemp.toString());
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileTarget), env.getProperty("middleware.ledger.charSetName")));

            sbTemp.setLength(0);
            sbTemp.append(bankGeneralLedgerFileSource.getPaymentCount()).append(MyConstant.SPLIT).append(bankGeneralLedgerFileSource.getPaymentMount()).append(MyConstant.SPLIT)
                    .append(MyConstant.NEW_LINE);
            out.write(sbTemp.toString());
            for (String[] item : bankGeneralLedgerFileSource.getDetail()) {
                StringBuffer sb = new StringBuffer();
                GasUserBehavior gb = userBehaviorMap.get(item[0]);
                sb.append(BankPacketHeadEntity.B_SN_QZ + item[2]).append(MyConstant.SPLIT); // 银行业务流水
                sb.append(item[0]).append(MyConstant.SPLIT); // 用户号
                String tempMonth = gb.getValue3();
                sb.append(tempMonth.substring(tempMonth.length() - 7, tempMonth.length() - 1)).append(MyConstant.SPLIT); // 气费月份
                // sb.append(downLoadFileName.substring(0, 6)).append(MyConstant.SPLIT); // 气费月份
                String yjfy = item[1];
                String jffs = "1";
                if (gb != null) {
                    yjfy = gb.getValue2();
                    if ("01".equals(gb.getValue5()))
                        jffs = "1";
                    else if ("02".equals(gb.getValue5()))
                        jffs = "2";
                    else if ("03".equals(gb.getValue5()))
                        jffs = "3";
                    else if ("04".equals(gb.getValue5()))
                        jffs = "5";
                    else if ("05".equals(gb.getValue5()))
                        jffs = "4";
                }

                sb.append(new BigDecimal(yjfy).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()).append(MyConstant.SPLIT); // 应缴费用
                sb.append(new BigDecimal(item[1]).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()).append(MyConstant.SPLIT); // 实缴费用
                sb.append(item[3]).append(MyConstant.SPLIT); // 缴费日期
                sb.append(jffs).append(MyConstant.SPLIT); // 缴费方式
                sb.append(2).append(MyConstant.SPLIT); // 缴费类别
                sb.append(MyConstant.NEW_LINE);
                out.write(sb.toString());
            }

            // 清除缓存
            out.flush();
            if (!ftpUtil.upLoadByFtp(fileTarget.getPath(), "/", fileTarget.getName(), ftpGas))
                return false;
        } catch (Exception e) {
            logger.error("ftp错误!", e);
            return false;
        } finally {
            try {
                if (ftpBank != null)
                    ftpUtil.disconnect(ftpBank);

                if (ftpGas != null)
                    ftpUtil.disconnect(ftpBank);

                if (in != null)
                    in.close();

                if (out != null)
                    out.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return true;
    }
}
