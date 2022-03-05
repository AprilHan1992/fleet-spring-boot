package com.fleet.rmi.provider.config;

import com.fleet.rmi.common.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Configuration
public class RmiServer {

    @Resource
    UserService userService;

    @Bean
    public RmiServiceExporter rmiServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("userService");
        rmiServiceExporter.setService(userService);
        rmiServiceExporter.setServiceInterface(UserService.class);
        rmiServiceExporter.setRegistryPort(6001);
        return rmiServiceExporter;
    }
}
