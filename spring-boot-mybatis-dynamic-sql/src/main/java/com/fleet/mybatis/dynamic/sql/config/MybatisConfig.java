package com.fleet.mybatis.dynamic.sql.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author April Han
 */
@Configuration
@MapperScan({"com.fleet.mybatis.dynamic.sql.dao"})
public class MybatisConfig {
}
