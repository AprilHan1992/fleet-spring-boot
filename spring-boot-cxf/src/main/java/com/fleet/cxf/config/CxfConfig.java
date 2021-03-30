package com.fleet.cxf.config;

import com.fleet.cxf.service.UserService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.xml.ws.Endpoint;

/**
 * @author April Han
 */
@Configuration
public class CxfConfig {

//    @Bean(name = Bus.DEFAULT_BUS_ID)
//    public SpringBus springBus() {
//        return new SpringBus();
//    }

    @Resource
    private Bus bus;

    @Resource
    UserService userService;

//    @Bean
//    public UserService userService() {
//        return new UserServiceImpl();
//    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        endpoint.publish("/userService");
        return endpoint;
    }
}
