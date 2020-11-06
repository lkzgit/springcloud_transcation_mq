package com.roc.bank1.Feign;

import com.roc.bank1.entity.AccountPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "BESTROC-BANK2-SERVICE",fallback = PayFallback.class)
@Component
public interface PayClient {

    //远程调用充值系统的接口查询充值结果
    @GetMapping(value = "/pay/payresult/{txNo}")
    public AccountPay payresult(@PathVariable("txNo") String txNo);

    @GetMapping(value = "/pay/test")
    public String test();
}
