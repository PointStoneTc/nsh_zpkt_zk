package com.zkpt.platform.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkpt.platform.base.BaseController;
import com.zkpt.platform.entity.AnalyYMOperationTimes;
import com.zkpt.platform.entity.AnalyYMPaymentStream;
import com.zkpt.platform.service.AnalysisService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/platform/analysis")
public class AnalysisController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(AnalysisController.class);
    @Autowired
    private AnalysisService analysisService;


    @RequestMapping("list")
    public String index(Model model, HttpServletRequest request) {
        return "platform/analysis/analysisList";
    }

    /**
     * 封装list页面数据
     */
    @RequestMapping(value = "listDatagrid", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject listDatagrid() {
        JSONObject res = new JSONObject();
        List<AnalyYMPaymentStream> psymList = analysisService.paymentStreamAmountByYM(null, null);
        List<AnalyYMOperationTimes> otymList = analysisService.operatoinTimesByYM(null, null);
        res.put("psym", psymList);
        res.put("otym", otymList);
        return res;
    }

    /**
     * 查询指定年、月的日收入统计
     * 
     * @param year
     * @param month
     * @return
     */
    @RequestMapping(value = "paymentStreamAmountByYM", method = RequestMethod.POST)
    @ResponseBody
    public List<AnalyYMPaymentStream> paymentStreamAmountByYM(Integer year, Integer month) {
        return analysisService.paymentStreamAmountByYM(year, month);
    }

    /**
     * 查询指定年、月的日请求访问次数
     * 
     * @param year
     * @param month
     * @return
     */
    @RequestMapping(value = "operatoinTimesByYM", method = RequestMethod.POST)
    @ResponseBody
    public List<AnalyYMOperationTimes> operatoinTimesByYM(Integer year, Integer month) {
        return analysisService.operatoinTimesByYM(year, month);
    }
}
