package com.qf.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Account implements Serializable {

    private int id;//银行卡id
    private int bank_card;//银行卡号
    private String bank_name;//银行名称
    private int balance;//银行卡余额
}
