package com.demo.account.controller;


import com.demo.account.dto.AccountReduceBalanceDTO;
import com.demo.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("/reduce-balance")
    public void reduceBalance(@RequestParam("userId")Long userId,@RequestParam("price")Integer price) throws Exception {
        logger.info("[reduceBalance] 收到减少余额请求, 用户:{}, 金额:{}", userId,
                price);
        accountService.reduceBalance(userId, price);
    }
    @GetMapping("/test")
    public  String test(@RequestParam("name")String name){
        return "张三";
    };
}
