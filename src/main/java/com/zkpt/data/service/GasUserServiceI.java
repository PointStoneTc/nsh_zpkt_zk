package com.zkpt.data.service;

import com.common.mybatis.PageBean;
import com.zkpt.data.entity.GasUser;
import com.zkpt.data.view.UserView;

public interface GasUserServiceI {
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
    public int insert(GasUser record);

    /**
     * 
     * @param record
     * @return
     */
    public int insertSelective(GasUser record);

    /**
     * 
     * @param id
     * @return
     */
    public GasUser selectByPrimaryKey(Integer id);

    /**
     * 
     * @param userNo
     * @return
     */
    public GasUser selectByUserNo(String userNo);

    /**
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(GasUser record);

    /**
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(GasUser record);

    /**
     * 
     * @param userView
     * @param page
     * @param rows
     * @return
     */
    public PageBean<UserView> selectAll(UserView userView, int page, int rows);
}
