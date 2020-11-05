package com.roc.bank.message;


import com.alibaba.fastjson.JSONObject;
import com.roc.bank.mapper.AccountMapper;
import com.roc.bank.model.AccountChangeEvent;
import com.roc.bank.service.AccountService;
import lombok.extern.slf4j.Slf4j;


import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @version 1.0
 **/
@Component
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "producer_group_txmsg_bank1") //和发消息的生产者一致
public class ProducerTxmsgListener implements RocketMQLocalTransactionListener {

    @Autowired
    AccountService accountInfoService;

    @Autowired
    AccountMapper accountInfoDao;

    //事务消息发送后的回调方法，当消息发送给mq成功，此方法被回调
    @Transactional
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(org.springframework.messaging.Message message, Object o) {
        try {
            //解析message，转成AccountChangeEvent
            String messageString = new String((byte[]) message.getPayload());
            JSONObject jsonObject = JSONObject.parseObject(messageString);
            String accountChangeString = jsonObject.getString("accountChange");
            //将accountChange（json）转成AccountChangeEvent
            AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);
            //执行本地事务，扣减金额
            accountInfoService.doUpdateAccountBalance(accountChangeEvent);
            //当返回RocketMQLocalTransactionState.COMMIT，自动向mq发送commit消息，mq将消息的状态改为可消费
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }


    //事务状态回查，查询是否扣减金额
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(org.springframework.messaging.Message message) {
        //解析message，转成AccountChangeEvent
        String messageString = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(messageString);
        String accountChangeString = jsonObject.getString("accountChange");
        //将accountChange（json）转成AccountChangeEvent
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);
        //事务id
        String txNo = accountChangeEvent.getTxNo();
        int existTx = accountInfoDao.isExistTx(txNo);
        if(existTx>0){
            return RocketMQLocalTransactionState.COMMIT;
        }else{
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}
