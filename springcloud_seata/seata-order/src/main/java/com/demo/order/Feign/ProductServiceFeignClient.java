package com.demo.order.Feign;



import com.demo.order.dto.ProductReduceStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * `product-service` 服务的 Feign 客户端
 */
@FeignClient(name = "product-service")
public interface ProductServiceFeignClient {

    @GetMapping("/product/reduce-stock")
    void reduceStock(@RequestParam("productId") Long productId,@RequestParam("amount") Integer amount);

}


