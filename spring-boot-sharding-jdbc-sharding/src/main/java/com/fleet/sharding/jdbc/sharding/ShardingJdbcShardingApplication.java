package com.fleet.sharding.jdbc.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 分库分表
 */
@SpringBootApplication
public class ShardingJdbcShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcShardingApplication.class, args);
    }
}
