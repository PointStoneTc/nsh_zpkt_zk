package com.zkpt.data.service;

import java.util.List;

import com.zkpt.data.entity.AuthorizationArea;

public interface AuthorizationAreaServiceI {
    /**
     * 
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 
     * @param record
     * @return
     */
    int insert(AuthorizationArea record);

    /**
     * 
     * @param record
     * @return
     */
    int insertSelective(AuthorizationArea record);

    /**
     * 
     * @param id
     * @return
     */
    AuthorizationArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthorizationArea record);

    /**
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(AuthorizationArea record);

    /**
     * @Title:查询所有生效的地区
     * @return
     */
    List<AuthorizationArea> findAllEffect();
}
