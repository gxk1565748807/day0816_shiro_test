package com.qf.service;

import com.qf.pojo.Account;

public interface AccountService {


    /**
     * 根据当前用户id查询其所有的银行卡信息
     * @param userId
     * @return
     */
    public Account findAccountByUserId(int userId);
}
