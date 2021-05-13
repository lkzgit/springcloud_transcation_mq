package com.demo.order.Feign;



import com.demo.order.dto.AccountReduceBalanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * `account-service` 服务的 Feign 客户端
 */
@FeignClient(name = "account-service")
public interface AccountServiceFeignClient {

    @GetMapping("/account/reduce-balance")
    void reduceBalance(@RequestParam("userId")Long userId,@RequestParam("price")Integer price);

    @GetMapping("/account/test")
    String test(@RequestParam("name")String name);

}
