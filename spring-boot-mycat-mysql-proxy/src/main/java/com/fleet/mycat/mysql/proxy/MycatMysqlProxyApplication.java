package com.fleet.mycat.mysql.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 读写分离
 */
@SpringBootApplication
public class MycatMysqlProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycatMysqlProxyApplication.class, args);
	}
}
