package com.roc.bank1.Feign;

import com.roc.bank1.entity.AccountPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "bestRoc-bank2-service",fallback = PayFallback.class)
public interface PayClient {

    //远程调用充值系统的接口查询充值结果
    @GetMapping(value = "/pay/payresult/{txNo}")
    public AccountPay payresult(@PathVariable("txNo") String txNo);
}
