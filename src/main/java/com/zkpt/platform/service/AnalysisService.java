package com.zkpt.platform.service;

import java.util.List;

import com.zkpt.platform.entity.AnalyYMOperationTimes;
import com.zkpt.platform.entity.AnalyYMPaymentStream;

public interface AnalysisService {
    /**
     * 指定年、月统计收入
     * 
     * @param year
     * @param month
     * @return
     */
    List<AnalyYMPaymentStream> paymentStreamAmountByYM(Integer year, Integer month);

    /**
     * 指定年、月统计访问次数
     * 
     * @param year
     * @param month
     * @return
     */
    List<AnalyYMOperationTimes> operatoinTimesByYM(Integer year, Integer month);
}
