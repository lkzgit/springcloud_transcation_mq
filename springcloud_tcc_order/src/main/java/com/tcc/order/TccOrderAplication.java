package com.tcc.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class TccOrderAplication {

    public static void main(String[] args) {
        SpringApplication.run(TccOrderAplication.class,args);
    }
}
