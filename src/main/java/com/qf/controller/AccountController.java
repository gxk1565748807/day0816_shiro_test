package com.qf.controller;

import com.qf.pojo.Account;
import com.qf.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
