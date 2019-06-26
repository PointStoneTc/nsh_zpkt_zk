package com.zkpt.bank.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zkpt.bank.entity.BankProtocalPackage;
import com.zkpt.bank.entity.BankRespState;
import com.zkpt.bank.service.IBankUnUsefulService;
import com.zkpt.middleware.entity.MyConstant;

@Service("bankUnUsefulService")
@Transactional
public class BankUnUsefulServiceImpl extends BankServiceImpl implements IBankUnUsefulService {
    private final static Logger logger = LoggerFactory.getLogger(BankUnUsefulServiceImpl.class);
    private final static int splitCount = 10;

    @Override
    public boolean request(BankProtocalPackage bankProtocalPackage) throws Exception {
        errorWrite(BankRespState.OTHER_ERROR.getKey(), MyConstant.ERROR_COM_011, bankProtocalPackage, splitCount);
        return false;
    }
}
