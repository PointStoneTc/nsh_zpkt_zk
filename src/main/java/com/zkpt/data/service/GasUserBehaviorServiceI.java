package com.zkpt.data.service;

import java.util.List;

import com.common.mybatis.PageBean;
import com.zkpt.data.entity.GasUserBehavior;
import com.zkpt.data.view.StreamView;
import com.zkpt.data.view.UserPaymentView;

public interface GasUserBehaviorServiceI {
    /**
     * 
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Integer id);

    /**
     * 
     * @param record
     * @return
     */
    public int insert(GasUserBehavior record);

    /**
     * 
     * @param record
     * @return
     */
    public int insertSelective(GasUserBehavior record);

    /**
     * 
     * @param id
     * @return
     */
    public GasUserBehavior selectByPrimaryKey(Integer id);

    /**
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(GasUserBehavior record);

    /**
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(GasUserBehavior record);

    /**
     * 获取指定用户所有的缴费信息
     * 
     * @param userNo
     * @return
     */
    public PageBean<UserPaymentView> findUserAllPayment(String userNo);

    /**
     * 获取所有的流水信息
     * 
     * @param streamView
     * @param page
     * @param rows
     * @return
     */
    public PageBean<StreamView> findAllStream(StreamView streamView, int page, int rows);

    /**
     * 查询昨日所有用户的缴费及查询记录(value5是缴费方式, value2是欠费金额)
     * 
     * @return
     */
    public List<GasUserBehavior> findAllYesterdayStream();
}
