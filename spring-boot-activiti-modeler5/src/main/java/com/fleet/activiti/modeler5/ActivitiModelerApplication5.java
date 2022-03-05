package com.fleet.activiti.modeler5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class, org.activiti.spring.boot.SecurityAutoConfiguration.class})
@ComponentScan({"org.activiti.rest.diagram", "com.fleet.activiti.modeler5"})
public class ActivitiModelerApplication5 {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiModelerApplication5.class, args);
    }
}
