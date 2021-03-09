package com.fleet.motan.server.config;

import com.weibo.api.motan.config.springsupport.BasicServiceConfigBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author April Han
 */
@Configuration
public class MotanServerConfig {

    /**
     * Motan 服务端配置
     */
    @Bean(name = "serverBasicConfig")
    @ConfigurationProperties(prefix = "motan.server")
    public BasicServiceConfigBean basicServiceConfigBean() {
        return new BasicServiceConfigBean();
    }
}
