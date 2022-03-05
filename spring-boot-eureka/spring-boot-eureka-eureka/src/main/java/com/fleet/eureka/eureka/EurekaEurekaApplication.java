package com.fleet.eureka.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaEurekaApplication.class, args);
    }
}
