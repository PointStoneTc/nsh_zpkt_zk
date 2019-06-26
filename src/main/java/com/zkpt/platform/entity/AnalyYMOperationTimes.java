package com.zkpt.platform.entity;

public class AnalyYMOperationTimes {
    private int ct;
    private int day;
    private String bankCommand;

    public AnalyYMOperationTimes(int ct, int day, String bankCommand) {
        this.ct = ct;
        this.day = day;
        this.bankCommand = bankCommand;
    }

    public AnalyYMOperationTimes() {}

    public int getCt() {
        return ct;
    }

    public void setCt(int ct) {
        this.ct = ct;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getBankCommand() {
        return bankCommand;
    }

    public void setBankCommand(String bankCommand) {
        this.bankCommand = bankCommand;
    }

}
