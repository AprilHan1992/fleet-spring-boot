package com.fleet.sharding.jdbc.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 读写分离
 */
@SpringBootApplication
public class ShardingJdbcProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcProxyApplication.class, args);
    }
}
