package com.fleet.hessian.consumer.config;

import com.fleet.hessian.provider.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * @author April Han
 */
@Configuration
public class HessianConfig {

    @Bean
    public HessianProxyFactoryBean hessianProxyFactoryBean() {
        HessianProxyFactoryBean bean = new HessianProxyFactoryBean();
        bean.setServiceUrl("http://localhost:8001/user-service");
        bean.setServiceInterface(UserService.class);
        return bean;
    }
}
