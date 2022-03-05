package com.fleet.eureka.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Cloud RestTemplate 支持 ip、域名、服务名 调用
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 加 @LoadBalanced 注解，支持 服务名 调用
     */
    @Bean
    @LoadBalanced
    public RestTemplate loadBalanced() {
        return new RestTemplate();
    }

    /**
     * 不加 @LoadBalanced 注解，支持 ip、域名 调用
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
