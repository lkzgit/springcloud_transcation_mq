package com.roc.bank1.controller;


import com.roc.bank1.Feign.PayClient;
import com.roc.bank1.entity.AccountPay;
import com.roc.bank1.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("best")
public class BestController {


    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    PayClient payClient;

    //主动查询充值结果
    @GetMapping(value = "/payresult/{txNo}")
    public AccountPay result(@PathVariable("txNo") String txNo){
        AccountPay accountPay = accountInfoService.queryPayResult(txNo);
        return accountPay;
    }

    @GetMapping(value = "/test")
    public String test(){
       return payClient.test();
    }

}
