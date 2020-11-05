package com.roc.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Bank1Application {

    public static void main(String[] args) {
        SpringApplication.run(Bank1Application.class,args);
    }
}
