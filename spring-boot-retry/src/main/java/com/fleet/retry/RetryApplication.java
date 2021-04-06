package com.fleet.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author April Han
 */
@SpringBootApplication
@EnableRetry
public class RetryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetryApplication.class, args);
    }
}
