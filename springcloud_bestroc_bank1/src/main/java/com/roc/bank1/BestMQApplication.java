package com.roc.bank1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BestMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestMQApplication.class,args);
    }
}


