package com.fleet.forest.consumer;

import com.thebeastshop.forest.springboot.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ForestScan(basePackages = "com.fleet.forest.consumer.service")
public class ForestConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForestConsumerApplication.class, args);
    }
}
