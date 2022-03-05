package com.fleet.mycat.mysql.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 分库分表
 */
@SpringBootApplication
public class MycatMysqlShardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycatMysqlShardingApplication.class, args);
	}
}
