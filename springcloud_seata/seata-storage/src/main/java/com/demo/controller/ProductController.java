package com.demo.controller;


import com.demo.dto.ProductReduceStockDTO;
import com.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/reduce-stock")
    public void reduceStock(@RequestParam("productId") Long productId, @RequestParam("amount") Integer amount)
            throws Exception {
        logger.info("[reduceStock] 收到减少库存请求, 商品:{}, 价格:{}", productId,
                amount);
        productService.reduceStock(productId, amount);
    }

}
