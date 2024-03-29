package com.qf.controller;

import com.qf.pojo.Account;
import com.qf.pojo.Investment;
import com.qf.service.AccountService;
import com.xiaoleilu.hutool.json.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/loadByUserId")
    private String loadByUserId(int userId, Model model){

        List<Account> account = accountService.findAccountByUserId(userId);
        System.out.println(userId);
        System.out.println(account);
        model.addAttribute("account",account);
        return "mine";
    }
    @RequestMapping("/loadInvesByUserId")
    private String loadInvesByUserId(int userId,Model model){
        List<Investment> investments = accountService.loadInvestmentByUserId(userId);
        System.out.println(investments);
        model.addAttribute("investments",investments);
        return "investment";
    }


    @RequestMapping("investment")
    private String investments(){
        return "investment";
    }
}
