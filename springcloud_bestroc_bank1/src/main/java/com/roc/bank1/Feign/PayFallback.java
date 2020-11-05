package com.roc.bank1.Feign;

import com.roc.bank1.entity.AccountPay;
import org.springframework.stereotype.Component;

@Component
public class PayFallback implements PayClient {
    @Override
    public AccountPay payresult(String txNo) {
        AccountPay accountPay = new AccountPay();
        accountPay.setResult("fail");
        return accountPay;
    }
}