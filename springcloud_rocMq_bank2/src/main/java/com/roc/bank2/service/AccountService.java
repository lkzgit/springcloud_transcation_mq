package com.roc.bank2.service;

import com.common.model.CommonResult;

import com.roc.bank2.entity.AccountInfo;
import com.roc.bank2.mapper.AccountMapper;
import com.roc.bank2.model.AccountChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountService {



    @Autowired
    private AccountMapper accountMapper;

    public CommonResult findById(Integer id){
      AccountInfo info= accountMapper.findById(id);
      return new CommonResult(0000,"ok",info);
    }


    //更新账户，增加金额

    @Transactional
    public void addAccountInfoBalance(AccountChangeEvent accountChangeEvent) {
        log.info("bank2更新本地账号，账号：{},金额：{}",accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());
        if(accountMapper.isExistTx(accountChangeEvent.getTxNo())>0){

            return ;
        }
        //增加金额
        accountMapper.updateAccountBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());
        //添加事务记录，用于幂等
        accountMapper.addTx(accountChangeEvent.getTxNo());
        if(accountChangeEvent.getAmount() == 4){
            throw new RuntimeException("人为制造异常");
        }
    }

}
