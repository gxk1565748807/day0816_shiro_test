package com.qf.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Investment implements Serializable {
    private int id;//商品id
    private int fund;//基金
    private int shares;//股票
    private int insurance;//保险
    private int loan;//贷款
}
