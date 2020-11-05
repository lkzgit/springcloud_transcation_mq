package com.roc.bank1.service;

import com.roc.bank1.Feign.PayClient;
import com.roc.bank1.entity.AccountPay;
import com.roc.bank1.mapper.AccountInfoDao;
import com.roc.bank1.model.AccountChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountInfoService{


        @Autowired
        AccountInfoDao accountInfoDao;

        @Autowired
        PayClient payClient;

        //更新账户金额
        @Transactional
        public void updateAccountBalance(AccountChangeEvent accountChange) {
                //幂等校验
                if(accountInfoDao.isExistTx(accountChange.getTxNo())>0){
                return ;
                }
                int i = accountInfoDao.updateAccountBalance(accountChange.getAccountNo(), accountChange.getAmount());
                //插入事务记录，用于幂等控制
                accountInfoDao.addTx(accountChange.getTxNo());
                }

        //远程调用查询充值结果
        public AccountPay queryPayResult(String tx_no) {

                //远程调用
                AccountPay payresult = payClient.payresult(tx_no);
                if("success".equals(payresult.getResult())){
                //更新账户金额
                AccountChangeEvent accountChangeEvent = new AccountChangeEvent();
                accountChangeEvent.setAccountNo(payresult.getAccountNo());//账号
                accountChangeEvent.setAmount(payresult.getPayAmount());//金额
                accountChangeEvent.setTxNo(payresult.getId());//充值事务号
                updateAccountBalance(accountChangeEvent);
                }
                return payresult;
        }

}
