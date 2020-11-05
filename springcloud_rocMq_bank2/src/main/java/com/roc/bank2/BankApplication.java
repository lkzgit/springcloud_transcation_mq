package com.roc.bank2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BankApplication {

//    https://blog.csdn.net/fenglibing/article/details/92378090 docker安装

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class,args);
    }
}
