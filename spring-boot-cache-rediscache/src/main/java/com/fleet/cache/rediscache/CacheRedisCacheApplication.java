package com.fleet.cache.rediscache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheRedisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheRedisCacheApplication.class, args);
    }
}
