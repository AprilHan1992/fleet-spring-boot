package com.fleet.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用 spring-boot:run 启动项目，可正常访问
 *
 * @author April Han
 */
@SpringBootApplication
public class JspApplication {

    public static void main(String[] args) {
        SpringApplication.run(JspApplication.class, args);
    }
}
