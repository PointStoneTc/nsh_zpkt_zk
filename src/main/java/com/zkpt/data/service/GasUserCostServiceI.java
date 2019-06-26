package com.zkpt.data.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.mybatis.PageBean;
import com.zkpt.data.entity.GasUserCost;

public interface GasUserCostServiceI {
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
    public int insert(GasUserCost record);

    /**
     * 
     * @param record
     * @return
     */
    public int insertSelective(GasUserCost record);

    /**
     * 
     * @param id
     * @return
     */
    public GasUserCost selectByPrimaryKey(Integer id);

    /**
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(GasUserCost record);

    /**
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(GasUserCost record);

    /**
     * 
     * @param userNo
     * @param months
     * @return
     */
    List<GasUserCost> selectAllCostByUsernoMonths(@Param("userNo") String userNo, @Param("months") List<Integer> months);

    /**
     * 获取指定用户所有的欠费信息
     * 
     * @param userNo
     * @return
     */
    PageBean<GasUserCost> findUserAllCost(@Param("userNo") String userNo);
}
