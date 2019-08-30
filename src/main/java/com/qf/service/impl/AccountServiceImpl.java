package com.qf.service.impl;

import com.qf.mapper.AccountMapper;
import com.qf.pojo.Account;
import com.qf.pojo.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AccountServiceImpl implements com.qf.service.AccountService {

    //注入mapper依赖
    @Resource
    private AccountMapper accountMapper;


    //根据用户id查询当前用户的银行卡信息
    @Override
    public List<Account> findAccountByUserId(int userId) {
        List<Account> accountByUserId = accountMapper.findAccountByUserId(userId);
        return accountByUserId;
    }
    //根据当前用户id查询其银行卡的信息
    @Override
    public List<Investment> loadInvestmentByUserId(int userId) {
        List<Investment> investments = accountMapper.loadInvestmentByUserId(userId);
        return investments;
    }


}
