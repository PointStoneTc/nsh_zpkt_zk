package com.zkpt.data.service;

import java.util.List;

import com.common.mybatis.PageBean;
import com.zkpt.bank.entity.BankProtocalPackage;
import com.zkpt.data.entity.Operation;
import com.zkpt.data.view.OperationView;
import com.zkpt.gas.entity.GasCostArrearageRespDetailEntity;
import com.zkpt.gas.entity.GasProtocalPackage;

public interface OperationServiceI {
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
    public int insert(Operation record);

    /**
     * 
     * @param record
     * @return
     */
    int insertSelective(Operation record);

    /**
     * 
     * @param id
     * @return
     */
    public Operation selectByPrimaryKey(Integer id);

    /**
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(Operation record);

    /**
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(Operation record);

    /**
     * 保存协议(适用于用户查询欠费)
     * 
     * @param bankProtocal 银行发送协议包
     * @param gasProtocal 天然气返回协议包
     * @param userNo 用户编号
     * @param userName 用户名称
     * @param arrears 欠费金额
     * @param gasCostArrearageRespDetailList
     * @return
     */
    public boolean addOperationQueryArrears(BankProtocalPackage bankProtocal, GasProtocalPackage gasProtocal, String userNo, String userName, double arrears,
            List<GasCostArrearageRespDetailEntity> gasCostArrearageRespDetailList);

    /**
     * 保存协议(适用于用户缴费)
     * 
     * @param bankProtocal 银行发送协议包
     * @param gasProtocal 天然气返回协议包
     * @return
     */
    public boolean addOperationPayment(BankProtocalPackage bankProtocal, GasProtocalPackage gasProtocal);

    /**
     * 保存协议(适用于用户冲账)
     * 
     * @param bankProtocal 银行发送协议包
     * @param gasProtocal 天然气返回协议包
     * @return
     */
    public boolean addOperationCharge(BankProtocalPackage bankProtocal, GasProtocalPackage gasProtocal);

    /**
     * 保存协议(适用于对账)
     * 
     * @param bankProtocal 银行发送协议包
     * @param gasProtocal 天然气返回协议包
     * @return
     */
    public boolean addOperationLedger(BankProtocalPackage bankProtocal, GasProtocalPackage gasProtocal);

    /**
     * 查询所有记录
     * 
     * @param bankCommand
     * @param startTime
     * @param endTime
     * @param page
     * @param rows
     * @return
     */
    public PageBean<OperationView> selectAll(Integer bankCommand, String startTime, String endTime, int page, int rows);
}
