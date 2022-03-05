package com.fleet.redisson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author April Han
 */
@SpringBootApplication
@EnableCaching
public class RedissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedissonApplication.class, args);
    }
}
