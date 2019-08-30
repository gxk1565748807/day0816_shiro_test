package com.qf.service;

import com.qf.pojo.Account;

import java.util.List;

public interface AccountService {


    /**
     * 根据当前用户id查询其所有的银行卡信息
     * @param userId
     * @return
     */
    public List<Account> findAccountByUserId(int userId);
}
