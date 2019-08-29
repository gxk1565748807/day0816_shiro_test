package com.qf.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAccount implements Serializable {
    private int uid;//用户id
    private int aid;//银行卡id
}
