package com.zkpt.data.view;

import java.util.Date;

public class UserView {
    private Integer id;
    private String userName;
    private String userNo;

    private double sumAirVal;
    private double sumLateFee;
    private double sumPayAbleAirCost;
    private double maxAirVal;
    private double minAirVal;
    private double avgAirVal;

    private Integer sumOpt;
    private Date maxOptTime;
    private Date minOptTime;
    private Integer subOptDay;

    public UserView() {}

    public UserView(Integer id, String userName, String userNo, double sumAirVal, double sumLateFee, double sumPayAbleAirCost, double maxAirVal, double minAirVal, double avgAirVal,
            Integer sumOpt, Date maxOptTime, Date minOptTime, Integer subOptDay) {
        this.id = id;
        this.userName = userName;
        this.userNo = userNo;
        this.sumAirVal = sumAirVal;
        this.sumLateFee = sumLateFee;
        this.sumPayAbleAirCost = sumPayAbleAirCost;
        this.maxAirVal = maxAirVal;
        this.minAirVal = minAirVal;
        this.avgAirVal = avgAirVal;
        this.sumOpt = sumOpt;
        this.maxOptTime = maxOptTime;
        this.minOptTime = minOptTime;
        this.subOptDay = subOptDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public double getSumAirVal() {
        return sumAirVal;
    }

    public void setSumAirVal(double sumAirVal) {
        this.sumAirVal = sumAirVal;
    }

    public double getSumLateFee() {
        return sumLateFee;
    }

    public void setSumLateFee(double sumLateFee) {
        this.sumLateFee = sumLateFee;
    }

    public double getSumPayAbleAirCost() {
        return sumPayAbleAirCost;
    }

    public void setSumPayAbleAirCost(double sumPayAbleAirCost) {
        this.sumPayAbleAirCost = sumPayAbleAirCost;
    }

    public double getMaxAirVal() {
        return maxAirVal;
    }

    public void setMaxAirVal(double maxAirVal) {
        this.maxAirVal = maxAirVal;
    }

    public double getMinAirVal() {
        return minAirVal;
    }

    public void setMinAirVal(double minAirVal) {
        this.minAirVal = minAirVal;
    }

    public double getAvgAirVal() {
        return avgAirVal;
    }

    public void setAvgAirVal(double avgAirVal) {
        this.avgAirVal = avgAirVal;
    }

    public Integer getSumOpt() {
        return sumOpt;
    }

    public void setSumOpt(Integer sumOpt) {
        this.sumOpt = sumOpt;
    }

    public Date getMaxOptTime() {
        return maxOptTime;
    }

    public void setMaxOptTime(Date maxOptTime) {
        this.maxOptTime = maxOptTime;
    }

    public Date getMinOptTime() {
        return minOptTime;
    }

    public void setMinOptTime(Date minOptTime) {
        this.minOptTime = minOptTime;
    }

    public Integer getSubOptDay() {
        return subOptDay;
    }

    public void setSubOptDay(Integer subOptDay) {
        this.subOptDay = subOptDay;
    }
}
