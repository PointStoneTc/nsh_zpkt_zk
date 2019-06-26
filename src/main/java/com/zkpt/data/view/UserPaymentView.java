package com.zkpt.data.view;

import java.util.Date;

public class UserPaymentView {
    private Integer id;
    private String transactionFlow; // 流水号
    private String paymentSum; // 缴费金额
    private String operatorNo; // 操作工号
    private String month; // 缴费月份
    private Date createDate; // 缴费时间

    public UserPaymentView() {}

    public UserPaymentView(Integer id, String transactionFlow, String paymentSum, String operatorNo, String month, Date createDate) {
        this.id = id;
        this.transactionFlow = transactionFlow;
        this.paymentSum = paymentSum;
        this.operatorNo = operatorNo;
        this.month = month;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionFlow() {
        return transactionFlow;
    }

    public void setTransactionFlow(String transactionFlow) {
        this.transactionFlow = transactionFlow;
    }

    public String getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(String paymentSum) {
        this.paymentSum = paymentSum;
    }

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
