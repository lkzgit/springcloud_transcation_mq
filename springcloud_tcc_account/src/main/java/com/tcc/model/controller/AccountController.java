package com.tcc.model.controller;

import com.common.model.CommonResult;
import com.tcc.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("account")
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/test")
    public CommonResult test(){
        return new CommonResult(0000,"account");
    }

    @GetMapping("findAccountId")
    public CommonResult findAccount(@RequestParam("id")Integer id){
        return accountService.findAccount(id);
    }
}
