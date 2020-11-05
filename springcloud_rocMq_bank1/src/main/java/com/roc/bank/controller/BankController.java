package com.roc.bank.controller;

import com.common.model.CommonResult;
import com.roc.bank.model.AccountChangeEvent;
import com.roc.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("bank1")
public class BankController {

    @Autowired
    private AccountService accountService;

    @GetMapping("test")
    public CommonResult test(){
        return new CommonResult(0000,"bank1");
    }


    @GetMapping("finById")
    public CommonResult findById(@RequestParam("id")Integer id){
        return accountService.findById(id);
    }

    @GetMapping("test1")
    public CommonResult testAccount(@RequestParam("account")String account,
                                    @RequestParam("amount")Double amount){
        //生成事务id,作为消息内容发到mq中
        String tx_no = UUID.randomUUID().toString();
        AccountChangeEvent accountChangeEvent = new AccountChangeEvent(account, amount, tx_no);
        accountService.sendUpdateAccountBalance(accountChangeEvent);
        return new CommonResult(0000,"ok");

    }


}
