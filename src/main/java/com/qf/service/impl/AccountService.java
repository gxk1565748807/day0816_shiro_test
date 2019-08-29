package com.qf.service.impl;

import com.qf.mapper.AccountMapper;
import com.qf.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class AccountService implements com.qf.service.AccountService {

    //注入mapper依赖
    @Resource
    private AccountMapper accountMapper;

    //根据用户id查询当前用户的银行卡信息
    @Override
    public Account findAccountByUserId(int userId){
        Account account = accountMapper.findAccountByUserId(userId);
        return account;
    }
}
