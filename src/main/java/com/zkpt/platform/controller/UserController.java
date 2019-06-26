package com.zkpt.platform.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.zkpt.data.entity.GasUserCost;
import com.zkpt.data.service.GasUserBehaviorServiceI;
import com.zkpt.data.service.GasUserCostServiceI;
import com.zkpt.data.service.GasUserServiceI;
import com.zkpt.data.view.StreamView;
import com.zkpt.data.view.UserPaymentView;
import com.zkpt.data.view.UserView;
import com.zkpt.platform.base.BaseController;

@Controller
@RequestMapping("/platform/user")
public class UserController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private GasUserServiceI gasUserService;

    @Autowired
    GasUserBehaviorServiceI gasUserBehaviorService;

    @Autowired
    GasUserCostServiceI gasUserCostService;

    @RequestMapping("list")
    public String index(Model model, HttpServletRequest request) {
        return "platform/user/userList";
    }

    /**
     * 
     * @param bankCommand
     * @param startTime
     * @param endTime
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "userDatagrid", method = RequestMethod.GET)
    @ResponseBody
    public PageBean<UserView> userDatagrid(UserView userView, @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "rows", defaultValue = "20") int rows) {
        PageBean<UserView> list = gasUserService.selectAll(userView, page, rows);
        return list;
    }

    /**
     * 获取指定用户所有的欠费信息
     * 
     * @param userNo
     * @return
     */
    @RequestMapping(value = "findUserAllCost", method = RequestMethod.POST)
    @ResponseBody
    public PageBean<GasUserCost> findUserAllCost(@RequestParam(value = "userNo") String userNo) {
        PageBean<GasUserCost> list = gasUserCostService.findUserAllCost(userNo);
        return list;
    }

    /**
     * 获取指定用户所有的缴费信息
     * 
     * @param userNo
     * @return
     */
    @RequestMapping(value = "findUserAllPayment", method = RequestMethod.POST)
    @ResponseBody
    public PageBean<UserPaymentView> findUserAllPayment(@RequestParam(value = "userNo") String userNo) {
        PageBean<UserPaymentView> list = gasUserBehaviorService.findUserAllPayment(userNo);
        return list;
    }

    /**
     * 获取所有的缴费流水信息
     * 
     * @param model
     * @return
     */
    @RequestMapping("stream")
    public String stream(Model model) {
        return "platform/user/streamList";
    }

    /**
     * 取所有的流水信息_datagrid
     * 
     * @param streamView
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "streamDatagrid", method = RequestMethod.GET)
    @ResponseBody
    public PageBean<StreamView> streamDatagrid(StreamView streamView, @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "rows", defaultValue = "20") int rows) {
        PageBean<StreamView> list = gasUserBehaviorService.findAllStream(streamView, page, rows);
        return list;
    }
}
