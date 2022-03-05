package com.fleet.ws.services.config;

import com.fleet.ws.services.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.xml.ws.Endpoint;

/**
 * @author April Han
 */
@Configuration
public class WSConfig {

    @Resource
    UserService userService;

    @Bean
    public Endpoint endpoint() {
        return Endpoint.publish("http://127.0.0.1:8002/services/userService", userService);
    }
}
