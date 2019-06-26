package com.zkpt.bank.entity;

import com.common.util.ProtocolTxTUtil;
import com.common.util.TrickUitl;
import com.zkpt.middleware.entity.CombinationIndex;
import com.zkpt.middleware.entity.ReplyParent;

/**
 * 对总帐响应包
 * 
 * @author zhaoqi
 *
 */
public class BankGeneralLedgerRespEntity implements ReplyParent, java.io.Serializable {
    private static final long serialVersionUID = 7310040045254151900L;

    private char[] transactionCode; // 交易码
    private char[] responseCode; // 响应码
    private char[] paymentCount; // 缴费笔数
    private char[] paymentMount; // 缴费金额

    @CombinationIndex(index = 1)
    private String transactionCode_s;
    @CombinationIndex(index = 2)
    private String responseCode_s;
    @CombinationIndex(index = 3)
    private Double paymentCount_s;
    @CombinationIndex(index = 4)
    private Double paymentMount_s;

    private BankGeneralLedgerRespEntity self; // 自己的对象指针

    public char[] getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(char[] transactionCode) {
        this.transactionCode = transactionCode;
    }

    public char[] getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(char[] responseCode) {
        this.responseCode = responseCode;
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

    public String getResponseCode_s() {
        return responseCode_s;
    }

    public void setResponseCode_s(String responseCode_s) {
        this.responseCode_s = responseCode_s;
        this.responseCode = ProtocolTxTUtil.strToCharArray(responseCode_s);
    }

    public Double getPaymentCount_s() {
        return paymentCount_s;
    }

    public void setPaymentCount_s(Double paymentCount_s) {
        this.paymentCount_s = paymentCount_s;
        this.paymentCount = ProtocolTxTUtil.strToCharArray(paymentCount_s);
    }

    public Double getPaymentMount_s() {
        return paymentMount_s;
    }

    public void setPaymentMount_s(Double paymentMount_s) {
        this.paymentMount_s = paymentMount_s;
        this.paymentMount = ProtocolTxTUtil.strToCharArray(paymentMount_s);
    }

    public BankGeneralLedgerRespEntity getSelf() {
        return self;
    }

    public void setSelf(BankGeneralLedgerRespEntity self) {
        this.self = self;
    }

    @Override
    public String reply() {
        return TrickUitl.reply_resp_combination(self);
    }
}
