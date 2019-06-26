package com.zkpt.data.view;

import java.util.Date;

public class StreamView {
    private Integer id;
    private String userNo; // 用户号
    private String userName; // 用户名称
    private String transactionFlow; // 流水号
    private String value; // 交易数据,如果是821存储的是金额,如果是850存储的银行流水号
    private String bankCommand; // 银行命令
    private String bankCommandName; // 银行命令名称
    private Date createDate; // 交易时间

    public StreamView() {}

    public StreamView(Integer id, String userNo, String userName, String transactionFlow, String value, String bankCommand, String bankCommandName, Date createDate) {
        super();
        this.id = id;
        this.userNo = userNo;
        this.userName = userName;
        this.transactionFlow = transactionFlow;
        this.value = value;
        this.bankCommand = bankCommand;
        this.bankCommandName = bankCommandName;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTransactionFlow() {
        return transactionFlow;
    }

    public void setTransactionFlow(String transactionFlow) {
        this.transactionFlow = transactionFlow;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBankCommand() {
        return bankCommand;
    }

    public void setBankCommand(String bankCommand) {
        this.bankCommand = bankCommand;
    }

    public String getBankCommandName() {
        return bankCommandName;
    }

    public void setBankCommandName(String bankCommandName) {
        this.bankCommandName = bankCommandName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
