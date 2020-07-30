package com.fleet.restdocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class RestdocsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestdocsApplication.class, args);
    }
}
