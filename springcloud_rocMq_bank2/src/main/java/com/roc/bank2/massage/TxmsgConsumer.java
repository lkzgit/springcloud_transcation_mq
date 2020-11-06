package com.roc.bank2.massage;

import com.alibaba.fastjson.JSONObject;
import com.roc.bank2.model.AccountChangeEvent;
import com.roc.bank2.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumer_group_txmsg_bank2",topic = "topic_txmsg")
public class TxmsgConsumer implements RocketMQListener<String> {

    @Autowired
    AccountService accountService;

    //接收消息 如果执行失败 消费方会一直消费消息
    @Override
    public void onMessage(String message) {
        log.info("开始消费消息:{}",message);
        //解析消息
        JSONObject jsonObject = JSONObject.parseObject(message);
        String accountChangeString = jsonObject.getString("accountChange");
        //转成AccountChangeEvent
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);
        //设置账号为李四的
        accountChangeEvent.setAccountNo("2");
        //更新本地账户，增加金额
        accountService.addAccountInfoBalance(accountChangeEvent);

    }

}
