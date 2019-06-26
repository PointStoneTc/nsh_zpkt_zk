package com.zkpt.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.mybatis.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zkpt.data.dao.GasUserBehaviorMapper;
import com.zkpt.data.entity.GasUserBehavior;
import com.zkpt.data.service.GasUserBehaviorServiceI;
import com.zkpt.data.view.StreamView;
import com.zkpt.data.view.UserPaymentView;

@Service("gasUserBehaviorService")
@Transactional
public class GasUserBehaviorServiceImpl implements GasUserBehaviorServiceI {
    @Resource
    private GasUserBehaviorMapper gasUserBehaviorDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return gasUserBehaviorDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GasUserBehavior record) {
        return gasUserBehaviorDao.insert(record);
    }

    @Override
    public int insertSelective(GasUserBehavior record) {
        return gasUserBehaviorDao.insertSelective(record);
    }

    @Override
    public GasUserBehavior selectByPrimaryKey(Integer id) {
        return gasUserBehaviorDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GasUserBehavior record) {
        return gasUserBehaviorDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GasUserBehavior record) {
        return gasUserBehaviorDao.updateByPrimaryKey(record);
    }

    @Override
    public PageBean<UserPaymentView> findUserAllPayment(String userNo) {
        Page<UserPaymentView> list = gasUserBehaviorDao.findUserAllPayment(userNo);
        PageBean<UserPaymentView> pageBean = new PageBean<UserPaymentView>(list);
        return pageBean;
    }

    @Override
    public PageBean<StreamView> findAllStream(StreamView streamView, int page, int rows) {
        PageHelper.startPage(page, rows);
        Page<StreamView> list = gasUserBehaviorDao.findAllStream(streamView);
        PageBean<StreamView> pageBean = new PageBean<StreamView>(list);
        return pageBean;
    }

    @Override
    public List<GasUserBehavior> findAllYesterdayStream() {
        List<GasUserBehavior> list = gasUserBehaviorDao.findAllYesterdayStream();
        return list;
    }
}
