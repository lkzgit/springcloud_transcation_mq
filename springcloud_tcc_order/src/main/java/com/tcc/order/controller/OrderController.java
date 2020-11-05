package com.tcc.order.controller;

import com.common.model.CommonResult;
import com.tcc.order.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/test")
    public CommonResult test(){
        return new CommonResult(0000,"ok");
    }

    @GetMapping("findById")
    public CommonResult findById(@RequestParam("id")Integer id){
        return orderService.findById(id);
    }
}
