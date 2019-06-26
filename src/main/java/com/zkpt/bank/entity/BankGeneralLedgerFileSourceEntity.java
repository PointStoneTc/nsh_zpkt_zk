package com.zkpt.bank.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 对账文件实体类
 * 
 * @author zhaoqi
 *
 */
public class BankGeneralLedgerFileSourceEntity implements java.io.Serializable {
    private static final long serialVersionUID = 4412783676065739630L;

    private int paymentCount; // 业务总笔数
    private double paymentMount; // 业务总金额
    List<String[]> detail;

    public BankGeneralLedgerFileSourceEntity() {
        detail = new ArrayList<String[]>();
    }

    public int getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(int paymentCount) {
        this.paymentCount = paymentCount;
    }

    public double getPaymentMount() {
        return paymentMount;
    }

    public void setPaymentMount(double paymentMount) {
        this.paymentMount = paymentMount;
    }

    public List<String[]> getDetail() {
        return detail;
    }

    public void setDetail(List<String[]> detail) {
        this.detail = detail;
    }
}
