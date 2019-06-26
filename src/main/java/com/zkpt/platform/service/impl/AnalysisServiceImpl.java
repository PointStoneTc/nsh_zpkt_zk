package com.zkpt.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.util.DateUtils;
import com.zkpt.platform.dao.AnalysisMapper;
import com.zkpt.platform.entity.AnalyYMOperationTimes;
import com.zkpt.platform.entity.AnalyYMPaymentStream;
import com.zkpt.platform.service.AnalysisService;

@Service("analysisService")
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private AnalysisMapper analysisDao;

    @Override
    public List<AnalyYMPaymentStream> paymentStreamAmountByYM(Integer year, Integer month) {
        if (year == null || month == null) {
            year = Integer.valueOf(DateUtils.getYear());
            month = Integer.valueOf(DateUtils.getMonth());
        }
        List<AnalyYMPaymentStream> list = analysisDao.paymentStreamAmountByYM(year, month);
        return list;
    }

    @Override
    public List<AnalyYMOperationTimes> operatoinTimesByYM(Integer year, Integer month) {
        if (year == null || month == null) {
            year = Integer.valueOf(DateUtils.getYear());
            month = Integer.valueOf(DateUtils.getMonth());
        }
        List<AnalyYMOperationTimes> list = analysisDao.operatoinTimesByYM(year, month);
        return list;
    }
}
