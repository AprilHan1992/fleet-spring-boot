package com.fleet.jndi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author April Han
 */
@SpringBootApplication
public class JndiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JndiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(JndiApplication.class, args);
    }
}
