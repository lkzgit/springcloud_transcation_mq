package com.roc.bank2.controller;


import com.common.model.CommonResult;
import com.roc.bank2.service.AccountService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("bank2")
@RestController
public class Bank2Controller {

    @Autowired
    private AccountService accountService;
    @GetMapping("test")
    public CommonResult test(){
        return new CommonResult(0000,"bank2");
    }

    @GetMapping("findById")
    public CommonResult findById(@RequestParam("id")Integer id){
        return accountService.findById(id);
    }



}
