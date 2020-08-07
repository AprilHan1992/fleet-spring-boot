package com.fleet.zipkin.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author April Han
 */
@SpringBootApplication
@EnableEurekaServer
public class ZipkinEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinEurekaApplication.class, args);
    }
}
