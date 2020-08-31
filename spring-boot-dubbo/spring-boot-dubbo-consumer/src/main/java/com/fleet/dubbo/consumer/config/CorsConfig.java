package com.fleet.dubbo.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 *
 * @author April Han
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 跨域的地址，注意 127.0.0.1 != localhost
        corsConfiguration.addAllowedOrigin("*");
        // 跨域的请求头
        corsConfiguration.addAllowedHeader("*");
        // 跨域的请求方法
        corsConfiguration.addAllowedMethod("*");
        // 跨域是否支持证书
        corsConfiguration.setAllowCredentials(true);
        // 跨域有效接口
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
