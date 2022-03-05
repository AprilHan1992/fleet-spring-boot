package com.fleet.motan.client.config;

import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author April Han
 */
@Configuration
public class MotanClientConfig {

    /**
     * 客户端配置
     */
    @Bean(name = "clientBasicConfig")
    @ConfigurationProperties(prefix = "motan.client")
    public BasicRefererConfigBean basicRefererConfigBean() {
        return new BasicRefererConfigBean();
    }
}
