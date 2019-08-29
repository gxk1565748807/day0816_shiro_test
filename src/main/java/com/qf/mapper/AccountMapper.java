package com.qf.mapper;


import com.qf.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {


    /**
     * 根据当前用户id查询其所有的银行卡信息
     * @param userId
     * @return
     */
    public Account findAccountByUserId(int userId);
}
