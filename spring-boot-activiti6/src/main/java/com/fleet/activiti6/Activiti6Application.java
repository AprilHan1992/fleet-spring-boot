package com.fleet.activiti6;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author April Han
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Activiti6Application {

    public static void main(String[] args) {
        SpringApplication.run(Activiti6Application.class, args);
    }
}
