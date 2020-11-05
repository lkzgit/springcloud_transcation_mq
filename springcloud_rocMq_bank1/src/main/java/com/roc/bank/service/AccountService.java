package com.roc.bank.service;


import com.alibaba.fastjson.JSONObject;
import com.common.model.CommonResult;

import com.roc.bank.entity.AccountInfo;
import com.roc.bank.mapper.AccountMapper;
import com.roc.bank.model.AccountChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class AccountService {


    @Autowired
    private AccountMapper accountMapper;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public CommonResult findById(Integer id){
      AccountInfo info= accountMapper.findById(id);
      return new CommonResult(0000,"ok",info);
    }

    //向mq发送转账消息
    public void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {

        //将accountChangeEvent转成json
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("accountChange",accountChangeEvent);
        String jsonString = jsonObject.toJSONString();
        //生成message类型
        Message<String> message = MessageBuilder.withPayload(jsonString).build();
        //发送一条事务消息
        /**
         * String txProducerGroup 生产组
         * String destination topic，
         * Message<?> message, 消息内容
         * Object arg 参数
         */
        rocketMQTemplate.sendMessageInTransaction("producer_group_txmsg_bank1","topic_txmsg",message,null);

    }

    //更新账户，扣减金额

    @Transactional
    public void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        //幂等判断
        if(accountMapper.isExistTx(accountChangeEvent.getTxNo())>0){
            return ;
        }
        //扣减金额
        accountMapper.updateAccountBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount() * -1);
        //添加事务日志
        accountMapper.addTx(accountChangeEvent.getTxNo());
        if(accountChangeEvent.getAmount() == 3){
            throw new RuntimeException("人为制造异常");
        }
    }



}
