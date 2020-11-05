package com.tcc.model.service;



import cn.hutool.log.Log;
import com.common.model.CommonResult;
import com.tcc.model.entity.Account;
import com.tcc.model.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * 账户业务实现类
 * Created by zzyy on 2019/11/11.
 */
@Service
@Slf4j
public class AccountService  {




    @Resource
    AccountMapper accountDao;

    /**
     * 扣减账户余额
     */

    public void decrease(Long userId, BigDecimal money) {
        log.info("------->account-service中扣减账户余额开始");
        //模拟超时异常，全局事务回滚
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        accountDao.decrease(userId,money);
        log.info("------->account-service中扣减账户余额结束");
    }

    public CommonResult findAccount(Integer id) {
      Account aa=  accountDao.findAccountId(id);
      return new CommonResult(0000,"ok",aa);
    }
}
