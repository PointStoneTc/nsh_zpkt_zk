package com.zkpt.data.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.mybatis.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zkpt.data.dao.GasUserMapper;
import com.zkpt.data.entity.GasUser;
import com.zkpt.data.service.GasUserServiceI;
import com.zkpt.data.view.UserView;

@Service("gasUserService")
@Transactional
public class GasUserServiceImpl implements GasUserServiceI {
    @Resource
    private GasUserMapper gasUserDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return gasUserDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GasUser record) {
        return gasUserDao.insert(record);
    }

    @Override
    public int insertSelective(GasUser record) {
        return gasUserDao.insertSelective(record);
    }

    @Override
    public GasUser selectByPrimaryKey(Integer id) {
        return gasUserDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GasUser record) {
        return gasUserDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GasUser record) {
        return gasUserDao.updateByPrimaryKey(record);
    }

    @Override
    public GasUser selectByUserNo(String userNo) {
        return gasUserDao.selectByUserNo(userNo);
    }

    @Override
    public PageBean<UserView> selectAll(UserView userView, int page, int rows) {
        PageHelper.startPage(page, rows);
        Page<UserView> list = gasUserDao.selectAll(userView);
        PageBean<UserView> pageBean = new PageBean<UserView>(list);
        return pageBean;
    }
}
