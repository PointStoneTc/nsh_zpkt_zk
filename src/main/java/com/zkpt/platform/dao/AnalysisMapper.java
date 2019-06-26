package com.zkpt.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zkpt.platform.entity.AnalyYMOperationTimes;
import com.zkpt.platform.entity.AnalyYMPaymentStream;

@Mapper
public interface AnalysisMapper {
    /**
     * 
     * @param year
     * @param month
     * @return
     */
    List<AnalyYMPaymentStream> paymentStreamAmountByYM(@Param("year") int year, @Param("month") int month);

    /**
     * 
     * @param year
     * @param month
     * @return
     */
    List<AnalyYMOperationTimes> operatoinTimesByYM(@Param("year") int year, @Param("month") int month);
}
