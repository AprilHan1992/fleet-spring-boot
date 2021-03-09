package com.fleet.motan.server.config;

import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author April Han
 */
@Configuration
public class MotanConfig {

    /**
     * 声明 Annotation 用来指定需要解析的包名
     */
    @Bean
    @ConfigurationProperties(prefix = "motan.annotation")
    public static AnnotationBean annotationBean() {
        return new AnnotationBean();
    }

    /**
     * Motan 协议配置
     */
    @Bean(name = "motan")
    @ConfigurationProperties(prefix = "motan.protocol")
    public ProtocolConfigBean protocolConfigBean() {
        return new ProtocolConfigBean();
    }

    /**
     * Motan 注册中心配置
     */
    @Bean(name = "registryConfig")
    @ConfigurationProperties(prefix = "motan.registry")
    public RegistryConfigBean registryConfigBean() {
        return new RegistryConfigBean();
    }
}
