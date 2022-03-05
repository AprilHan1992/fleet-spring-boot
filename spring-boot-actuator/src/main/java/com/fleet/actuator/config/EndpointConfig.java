package com.fleet.actuator.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfig {

    @Bean
    @ConditionalOnMissingBean
    public Hello hello() {
        return new Hello();
    }
}
