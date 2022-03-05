package com.fleet.jersey.config;

import com.fleet.jersey.service.UserService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(UserService.class);
    }
}
