package com.tcc.order.service;

import com.common.model.CommonResult;
import com.tcc.order.entity.Order;
import com.tcc.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public CommonResult findById(Integer id){
      Order order=  orderMapper.findById(id);
      return new CommonResult(0000,"ok",order);
    }

}
