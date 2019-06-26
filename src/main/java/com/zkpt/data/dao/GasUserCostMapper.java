package com.zkpt.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.zkpt.data.entity.GasUserCost;

@Mapper
public interface GasUserCostMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * mid_gas_user_cost
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * mid_gas_user_cost
     *
     * @mbggenerated
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(GasUserCost record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * mid_gas_user_cost
     *
     * @mbggenerated
     */
    int insertSelective(GasUserCost record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * mid_gas_user_cost
     *
     * @mbggenerated
     */
    GasUserCost selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * mid_gas_user_cost
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GasUserCost record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * mid_gas_user_cost
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GasUserCost record);

    /**
     * 
     * @param userNo
     * @param months
     * @return
     */
    List<GasUserCost> selectAllCostByUsernoMonths(@Param("userNo") String userNo, @Param("months") List<Integer> months);

    /**
     * 
     * @param userNo
     * @return
     */
    Page<GasUserCost> findUserAllCost(@Param("userNo") String userNo);
}
