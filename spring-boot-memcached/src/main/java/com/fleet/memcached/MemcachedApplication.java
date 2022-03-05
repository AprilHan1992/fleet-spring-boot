package com.fleet.memcached;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MemcachedApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemcachedApplication.class, args);
    }
}
