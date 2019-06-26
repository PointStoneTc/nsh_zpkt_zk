package com.zkpt.platform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.mybatis.PageBean;
import com.zkpt.data.service.OperationServiceI;
import com.zkpt.data.view.OperationView;
import com.zkpt.platform.base.BaseController;

@Controller
@RequestMapping("/platform/operation")
public class OperationController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(OperationController.class);
    @Autowired
    private OperationServiceI operationService;

    @RequestMapping("list")
    public String index(Model model) {
        return "platform/operation/operationList";
    }

    /**
     * 
     * @param bankCommand
     * @param startTime
     * @param endTime
     * @param page
     * @param rows
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "operationDatagrid", method = RequestMethod.GET)
    @ResponseBody
    public PageBean<OperationView> operationDatagrid(@RequestParam(required = false) Integer bankCommand, @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "rows", defaultValue = "20") int rows,
            HttpServletRequest request, HttpServletResponse response) {
        PageBean<OperationView> list = operationService.selectAll(bankCommand, startTime, endTime, page, rows);
        return list;
    }
}
