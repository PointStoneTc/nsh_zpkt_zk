package com.zkpt.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zkpt.data.dao.AuthorizationAreaMapper;
import com.zkpt.data.entity.AuthorizationArea;
import com.zkpt.data.service.AuthorizationAreaServiceI;

@Service("authorizationAreaService")
@Transactional
public class AuthorizationAreaServiceImpl implements AuthorizationAreaServiceI {
    @Resource
    private AuthorizationAreaMapper authorizationAreaDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return authorizationAreaDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AuthorizationArea record) {
        return authorizationAreaDao.insert(record);
    }

    @Override
    public int insertSelective(AuthorizationArea record) {
        return authorizationAreaDao.insertSelective(record);
    }

    @Override
    public AuthorizationArea selectByPrimaryKey(Integer id) {
        return authorizationAreaDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AuthorizationArea record) {
        return authorizationAreaDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AuthorizationArea record) {
        return authorizationAreaDao.updateByPrimaryKey(record);
    }

    @Override
    public List<AuthorizationArea> findAllEffect() {
        List<AuthorizationArea> list = authorizationAreaDao.findAllEffect();
        return list;
    }
}
