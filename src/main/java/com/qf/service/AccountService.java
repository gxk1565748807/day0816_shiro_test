package com.qf.service;

import com.qf.pojo.Account;
import com.qf.pojo.Investment;

import java.util.List;

public interface AccountService {


    /**
     * 根据当前用户id查询其所有的银行卡信息
     * @param userId
     * @return
     */
    public List<Account> findAccountByUserId(int userId);

    /**
     * 根据用户id查询其所投资的商品信息
     * @param userId
     * @return
     */
    public List<Investment> loadInvestmentByUserId(int userId);
}
