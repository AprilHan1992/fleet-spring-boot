package com.fleet.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 方式一
 * 利用 @ServletComponentScan 注解扫描过滤器
 *
 * @author April Han
 */
@ServletComponentScan(basePackages = {"com.fleet.filter.config.filter"})
@SpringBootApplication
public class FilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterApplication.class, args);
    }
}
