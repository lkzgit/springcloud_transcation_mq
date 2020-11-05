package com.roc.bank2.service;


import com.roc.bank2.entity.AccountPay;
import com.roc.bank2.mapper.AccountPayDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountPayService {


    @Autowired
    AccountPayDao accountPayDao;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    //插入充值记录
    @Transactional
    public AccountPay insertAccountPay(AccountPay accountPay) {
        int success = accountPayDao.insertAccountPay(accountPay.getId(), accountPay.getAccountNo(), accountPay.getPayAmount(), "success");
        if(success>0){
            //发送通知,使用普通消息发送通知
            accountPay.setResult("success");
            rocketMQTemplate.convertAndSend("topic_notifymsg",accountPay);
            return accountPay;
        }
        return null;
    }

    //查询充值记录，接收通知方调用此方法来查询充值结果
    public AccountPay getAccountPay(String txNo) {
        AccountPay accountPay = accountPayDao.findByIdTxNo(txNo);
        return accountPay;
    }

}
