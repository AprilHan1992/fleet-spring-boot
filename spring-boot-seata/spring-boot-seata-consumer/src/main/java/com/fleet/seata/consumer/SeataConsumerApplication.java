package com.fleet.seata.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SeataConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataConsumerApplication.class, args);
    }
}
