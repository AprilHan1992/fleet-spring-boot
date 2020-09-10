package com.fleet.flowable;

import com.fleet.flowable.config.AppDispatcherServletConfiguration;
import com.fleet.flowable.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author April Han
 */
@Import({ApplicationConfiguration.class, AppDispatcherServletConfiguration.class})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FlowableApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableApplication.class, args);
    }
}
