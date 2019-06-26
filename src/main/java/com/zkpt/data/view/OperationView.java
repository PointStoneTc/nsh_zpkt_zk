package com.zkpt.data.view;

import java.util.Date;

public class OperationView {
    private Integer id;
    private String bankIp;
    private Integer bankPort;
    private String bankSend;
    private Date bankSendTime;
    private String gasEcho;
    private Date gasEchoTime;
    private String bankCommand;
    private String state;
    private String createBy;
    private Date createDate;

    // 计算列
    private int timeInterval;

    public OperationView() {}

    public OperationView(Integer id, String bankIp, Integer bankPort, String bankSend, Date bankSendTime, String gasEcho, Date gasEchoTime, String bankCommand, String state,
            String createBy, Date createDate, int timeInterval) {
        this.id = id;
        this.bankIp = bankIp;
        this.bankPort = bankPort;
        this.bankSend = bankSend;
        this.bankSendTime = bankSendTime;
        this.gasEcho = gasEcho;
        this.gasEchoTime = gasEchoTime;
        this.bankCommand = bankCommand;
        this.state = state;
        this.createBy = createBy;
        this.createDate = createDate;
        this.timeInterval = timeInterval;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankIp() {
        return bankIp;
    }

    public void setBankIp(String bankIp) {
        this.bankIp = bankIp;
    }

    public Integer getBankPort() {
        return bankPort;
    }

    public void setBankPort(Integer bankPort) {
        this.bankPort = bankPort;
    }

    public String getBankSend() {
        return bankSend;
    }

    public void setBankSend(String bankSend) {
        this.bankSend = bankSend;
    }

    public Date getBankSendTime() {
        return bankSendTime;
    }

    public void setBankSendTime(Date bankSendTime) {
        this.bankSendTime = bankSendTime;
    }

    public String getGasEcho() {
        return gasEcho;
    }

    public void setGasEcho(String gasEcho) {
        this.gasEcho = gasEcho;
    }

    public Date getGasEchoTime() {
        return gasEchoTime;
    }

    public void setGasEchoTime(Date gasEchoTime) {
        this.gasEchoTime = gasEchoTime;
    }

    public String getBankCommand() {
        return bankCommand;
    }

    public void setBankCommand(String bankCommand) {
        this.bankCommand = bankCommand;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

}
