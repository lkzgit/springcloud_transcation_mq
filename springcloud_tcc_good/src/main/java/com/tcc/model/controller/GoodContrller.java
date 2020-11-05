package com.tcc.model.controller;

import com.common.model.CommonResult;
import com.tcc.model.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("good")
public class GoodContrller {

    @Autowired
    private StorageService storageService;
    @GetMapping("/test")
    public CommonResult test(){
        return new CommonResult(0000,"good");
    }

    @GetMapping("findByGoodId")
    public CommonResult findBYGoodId(@RequestParam("id")Integer id){
        return storageService.findByGoodId(id);
    }
}
