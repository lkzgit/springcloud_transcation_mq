package com.roc.bank2.controller;


import cn.hutool.core.lang.UUID;
import com.roc.bank2.entity.AccountPay;
import com.roc.bank2.service.AccountPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("pay")
@RestController
public class BestController {


    @Autowired
    AccountPayService accountPayService;

    //充值
    @GetMapping(value = "/paydo")
    public AccountPay pay(AccountPay accountPay){
        //生成事务编号
        String txNo = UUID.randomUUID().toString();
        accountPay.setId(txNo);
        return accountPayService.insertAccountPay(accountPay);
    }

    //查询充值结果
    @GetMapping(value = "/payresult/{txNo}")
    public AccountPay payresult(@PathVariable("txNo") String txNo){
        return accountPayService.getAccountPay(txNo);
    }


}
