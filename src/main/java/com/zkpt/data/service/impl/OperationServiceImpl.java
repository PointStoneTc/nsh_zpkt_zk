package com.zkpt.data.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.mybatis.PageBean;
import com.common.util.CommonUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zkpt.bank.entity.BankProtocalPackage;
import com.zkpt.data.dao.OperationMapper;
import com.zkpt.data.entity.GasUser;
import com.zkpt.data.entity.GasUserBehavior;
import com.zkpt.data.entity.GasUserCost;
import com.zkpt.data.entity.Operation;
import com.zkpt.data.service.GasUserBehaviorServiceI;
import com.zkpt.data.service.GasUserCostServiceI;
import com.zkpt.data.service.GasUserServiceI;
import com.zkpt.data.service.OperationServiceI;
import com.zkpt.data.view.OperationView;
import com.zkpt.gas.entity.GasCostArrearageRespDetailEntity;
import com.zkpt.gas.entity.GasProtocalPackage;
import com.zkpt.middleware.entity.MyConstant;

@Service("operationService")
@Transactional
public class OperationServiceImpl implements OperationServiceI {
    @Resource
    private OperationMapper operationDao;

    @Resource
    private GasUserServiceI gasUserService;

    @Resource
    private GasUserBehaviorServiceI gasUserBehaviorService;

    @Resource
    private GasUserCostServiceI gasUserCostService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return operationDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Operation record) {
        return operationDao.insert(record);
    }

    @Override
    public int insertSelective(Operation record) {
        return operationDao.insertSelective(record);
    }

    @Override
    public Operation selectByPrimaryKey(Integer id) {
        return operationDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Operation record) {
        return operationDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Operation record) {
        return operationDao.updateByPrimaryKey(record);
    }

    @Override
    public boolean addOperationQueryArrears(BankProtocalPackage bankProtocal, GasProtocalPackage gasProtocal, String userNo, String userName, double arrears,
            List<GasCostArrearageRespDetailEntity> gasCostArrearageRespDetailList) {
        Date date = new Date();
        // 1.获取用户账户信息，如果数据库不存在此用户，新增用户
        if (gasUserService.selectByUserNo(userNo) == null) {
            GasUser gasUser = new GasUser();
            gasUser.setUserNo(userNo);
            gasUser.setUserName(userName);
            gasUser.setCreateBy(MyConstant.JPA_SYS);
            gasUser.setCreateDate(date);
            gasUser.setUpdateBy(MyConstant.JPA_SYS);
            gasUser.setUpdateDate(date);
            gasUser.setDelflag(MyConstant.JPA_DELFLAG);
            gasUserService.insert(gasUser);
        }

        // 2.保存operation表
        Operation operation = ao(bankProtocal, gasProtocal);
        // 3.保存用户欠费情况表
        List<Integer> months = new ArrayList<Integer>();
        StringBuffer sb = new StringBuffer();
        for (GasCostArrearageRespDetailEntity item : gasCostArrearageRespDetailList) {
            months.add(Integer.valueOf(item.getMonth_s()));
            sb.append(item.getMonth_s()).append(MyConstant.SPLIT);
        }

        List<GasUserCost> findGasUserCosts = gasUserCostService.selectAllCostByUsernoMonths(userNo, months);

        if (months.size() - findGasUserCosts.size() > 0) { // 有新的欠费信息
            for (GasCostArrearageRespDetailEntity gasCostArrearageRespDetail : gasCostArrearageRespDetailList) {
                boolean notFindMonth = true;
                for (GasUserCost item : findGasUserCosts)
                    if (Integer.parseInt(gasCostArrearageRespDetail.getMonth_s()) == item.getMonth().intValue()) {
                        notFindMonth = false;
                        break;
                    }

                if (notFindMonth) {
                    GasUserCost gasUserCost = new GasUserCost();
                    gasUserCost.setUserName(gasCostArrearageRespDetail.getUserName_s());
                    gasUserCost.setUserNo(gasCostArrearageRespDetail.getUserNo_s());
                    gasUserCost.setMonth(Integer.valueOf(gasCostArrearageRespDetail.getMonth_s()));
                    gasUserCost.setPrevVal(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getPrevVal_s()));
                    gasUserCost.setCurrVal(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getCurrVal_s()));
                    gasUserCost.setAirVal(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getAirVal_s()));
                    gasUserCost.setAirCost(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getAirCost_s()));
                    gasUserCost.setPayAbleAirNum(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getPayAbleAirNum_s()));
                    gasUserCost.setLateFee(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getLateFee_s()));
                    gasUserCost.setPayAbleAirCost(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getPayAbleAirCost_s()));
                    gasUserCost.setAirVal1(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getAirVal1_s()));
                    gasUserCost.setAirCost1(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getAirCost1_s()));
                    gasUserCost.setAirVal2(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getAirVal2_s()));
                    gasUserCost.setAirCost2(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getAirCost2_s()));
                    gasUserCost.setAirVal3(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getAirVal3_s()));
                    gasUserCost.setAirCost3(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getAirCost3_s()));
                    gasUserCost.setAirVal4(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getAirVal4_s()));
                    gasUserCost.setAirCost4(CommonUtils.obcToBigDecimal(gasCostArrearageRespDetail.getAirCost4_s()));
                    gasUserCost.setCreateBy(MyConstant.JPA_SYS);
                    gasUserCost.setCreateDate(date);
                    gasUserCost.setUpdateBy(MyConstant.JPA_SYS);
                    gasUserCost.setUpdateDate(date);
                    gasUserCost.setDelflag(MyConstant.JPA_DELFLAG);
                    gasUserCostService.insert(gasUserCost);
                }
            }
        }

        // 4.保存用户行为表
        GasUserBehavior gasUserBehavior = new GasUserBehavior();
        gasUserBehavior.setOpertionId(operation.getId());
        gasUserBehavior.setUserNo(userNo); // 用户编号
        gasUserBehavior.setValue1(bankProtocal.getPacketHead().getTransactionFlow_s().trim()); // 流水号
        gasUserBehavior.setValue2(String.valueOf(arrears)); // 欠费总额
        gasUserBehavior.setValue3(sb.toString()); // 欠费月份
        gasUserBehavior.setCreateBy(MyConstant.JPA_SYS);
        gasUserBehavior.setCreateDate(date);
        gasUserBehavior.setUpdateBy(MyConstant.JPA_SYS);
        gasUserBehavior.setUpdateDate(date);
        gasUserBehavior.setDelflag(MyConstant.JPA_DELFLAG);
        gasUserBehaviorService.insert(gasUserBehavior);
        return true;
    }

    @Override
    public boolean addOperationPayment(BankProtocalPackage bankProtocal, GasProtocalPackage gasProtocal) {
        Date date = new Date();
        // 1.保存operation表
        Operation operation = ao(bankProtocal, gasProtocal);
        String[] temp = bankProtocal.getPacketBody().split(MyConstant.SPLIT1);

        // 2.保存用户行为表
        GasUserBehavior gasUserBehavior = new GasUserBehavior();
        gasUserBehavior.setOpertionId(operation.getId());
        gasUserBehavior.setUserNo(temp[1]); // 用户编号
        gasUserBehavior.setValue1(bankProtocal.getPacketHead().getTransactionFlow_s().trim()); // 流水号
        gasUserBehavior.setValue2(String.valueOf(temp[2])); // 缴费金额
        gasUserBehavior.setValue3(String.valueOf(temp[5])); // 操作工号
        gasUserBehavior.setValue4(String.valueOf(temp[6])); // 缴费月份
        gasUserBehavior.setValue5(bankProtocal.getPacketHead().getTransactionMode_s()); // 缴费方式
        gasUserBehavior.setCreateBy(MyConstant.JPA_SYS);
        gasUserBehavior.setCreateDate(date);
        gasUserBehavior.setUpdateBy(MyConstant.JPA_SYS);
        gasUserBehavior.setUpdateDate(date);
        gasUserBehavior.setDelflag(MyConstant.JPA_DELFLAG);
        gasUserBehaviorService.insert(gasUserBehavior);
        return true;
    }

    @Override
    public boolean addOperationCharge(BankProtocalPackage bankProtocal, GasProtocalPackage gasProtocal) {
        Date date = new Date();
        // 1.保存operation表
        Operation operation = ao(bankProtocal, gasProtocal);
        String[] temp = bankProtocal.getPacketBody().split(MyConstant.SPLIT1);

        // 2.保存用户行为表
        GasUserBehavior gasUserBehavior = new GasUserBehavior();
        gasUserBehavior.setOpertionId(operation.getId());
        gasUserBehavior.setUserNo(temp[1]); // 用户编号
        gasUserBehavior.setValue1(bankProtocal.getPacketHead().getTransactionFlow_s().trim()); // 流水号
        gasUserBehavior.setValue2(String.valueOf(temp[2])); // 银行流水号
        gasUserBehavior.setCreateBy(MyConstant.JPA_SYS);
        gasUserBehavior.setCreateDate(date);
        gasUserBehavior.setUpdateBy(MyConstant.JPA_SYS);
        gasUserBehavior.setUpdateDate(date);
        gasUserBehavior.setDelflag(MyConstant.JPA_DELFLAG);
        gasUserBehaviorService.insert(gasUserBehavior);
        return true;
    }

    @Override
    public boolean addOperationLedger(BankProtocalPackage bankProtocal, GasProtocalPackage gasProtocal) {
        ao(bankProtocal, gasProtocal);
        return true;
    }


    @Override
    public PageBean<OperationView> selectAll(Integer bankCommand, String startTime, String endTime, int page, int rows) {
        PageHelper.startPage(page, rows);
        Page<OperationView> list = operationDao.selectAll(bankCommand, startTime, endTime);
        PageBean<OperationView> pageBean = new PageBean<OperationView>(list);
        return pageBean;
    }

    /**
     * 添加一个Operation
     * 
     * @return
     */
    private Operation ao(BankProtocalPackage bankProtocal, GasProtocalPackage gasProtocal) {
        Date date = new Date();
        Operation operation = new Operation();
        operation.setBankIp(bankProtocal.getClientIp());
        operation.setBankPort(bankProtocal.getClientPort());
        operation.setBankSend(bankProtocal.getPacketHead_s() + bankProtocal.getPacketBody());
        operation.setBankSendTime(bankProtocal.getSentTime());
        operation.setBankSendTimeMillis(Long.valueOf(bankProtocal.getSentTimeMillis()));
        operation.setGasEcho(gasProtocal.getPacketHead_s() + gasProtocal.getPacketBody());
        operation.setGasEchoTime(gasProtocal.getEchoTime());
        operation.setGasEchoTimeMillis(Long.valueOf(gasProtocal.getEchoTimeMillis()));
        operation.setBankCommand(bankProtocal.getBankCommand().getKey());
        operation.setGasCommand(gasProtocal.getGasCommand().getKey());
        operation.setState(MyConstant.OPERAtION_STATE_SUCCESS);
        operation.setCreateBy(MyConstant.JPA_SYS);
        operation.setCreateDate(date);
        operation.setUpdateBy(MyConstant.JPA_SYS);
        operation.setUpdateDate(date);
        operation.setDelflag(MyConstant.JPA_DELFLAG);
        operationDao.insert(operation);
        return operation;
    }
}
