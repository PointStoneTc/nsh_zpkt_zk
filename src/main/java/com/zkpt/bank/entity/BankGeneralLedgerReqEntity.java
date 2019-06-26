package com.zkpt.bank.entity;

import com.common.util.ProtocolTxTUtil;
import com.zkpt.middleware.entity.MyConstant;

/**
 * 对总帐,命令字:600
 * 
 * @author zhaoqi
 *
 */
public class BankGeneralLedgerReqEntity extends BankParentReqEntity implements java.io.Serializable {
    private static final long serialVersionUID = -6323026085895838622L;

    private char[] transactionCode; // 交易码
    private char[] paymentDate; // 收费日期
    private char[] paymentCount; // 缴费笔数
    private char[] paymentMount; // 缴费金额

    private String transactionCode_s;
    private String paymentDate_s;
    private String paymentCount_s;
    private String paymentMount_s;

    public char[] getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(char[] transactionCode) {
        this.transactionCode = transactionCode;
    }

    public char[] getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(char[] paymentDate) {
        this.paymentDate = paymentDate;
    }

    public char[] getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(char[] paymentCount) {
        this.paymentCount = paymentCount;
    }

    public char[] getPaymentMount() {
        return paymentMount;
    }

    public void setPaymentMount(char[] paymentMount) {
        this.paymentMount = paymentMount;
    }

    public String getTransactionCode_s() {
        return transactionCode_s;
    }

    public void setTransactionCode_s(String transactionCode_s) {
        this.transactionCode_s = transactionCode_s;
        this.transactionCode = ProtocolTxTUtil.strToCharArray(transactionCode_s);
    }

    public String getPaymentDate_s() {
        return paymentDate_s;
    }

    public void setPaymentDate_s(String paymentDate_s) {
        this.paymentDate_s = paymentDate_s;
        this.paymentDate = ProtocolTxTUtil.strToCharArray(paymentDate_s);
    }

    public String getPaymentCount_s() {
        return paymentCount_s;
    }

    public void setPaymentCount_s(String paymentCount_s) {
        this.paymentCount_s = paymentCount_s;
        this.paymentCount = ProtocolTxTUtil.strToCharArray(paymentCount_s);
    }

    public String getPaymentMount_s() {
        return paymentMount_s;
    }

    public void setPaymentMount_s(String paymentMount_s) {
        this.paymentMount_s = paymentMount_s;
        this.paymentMount = ProtocolTxTUtil.strToCharArray(paymentMount_s);
    }

    public static BankParentReqEntity packaging(String packageBody) {
        BankGeneralLedgerReqEntity entity = new BankGeneralLedgerReqEntity();
        String[] packageBodys = packageBody.split(MyConstant.SPLIT1);
        entity.setTransactionCode_s(packageBodys[0]);
        entity.setPaymentDate_s(packageBodys[1]);
        entity.setPaymentCount_s(packageBodys[2]);
        entity.setPaymentMount_s(packageBodys[3]);
        return entity;
    }
}
