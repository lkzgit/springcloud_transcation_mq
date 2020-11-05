package com.roc.bank2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BestMQApplication2 {

    public static void main(String[] args) {
        SpringApplication.run(BestMQApplication2.class,args);
    }

}
