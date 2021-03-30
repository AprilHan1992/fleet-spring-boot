package com.fleet.gemfire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

/**
 * @author April Han
 */
@SpringBootApplication
@EnableGemfireRepositories
public class GemfireApplication {

    public static void main(String[] args) {
        SpringApplication.run(GemfireApplication.class, args);
    }
}
