package com.zkpt.platform.entity;

public class AnalyYMPaymentStream {
    private int ct;
    private double sumMoney;
    private int day;

    public AnalyYMPaymentStream() {}

    public AnalyYMPaymentStream(int ct, double sumMoney, int day) {
        this.ct = ct;
        this.sumMoney = sumMoney;
        this.day = day;
    }

    public int getCt() {
        return ct;
    }

    public void setCt(int ct) {
        this.ct = ct;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
