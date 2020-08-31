//package com.fleet.cors.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 跨域配置（二）
// *
// * @author April Han
// */
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        WebMvcConfigurer.super.addCorsMappings(registry);
//        registry
//                // 跨域有效接口
//                .addMapping("/**")
//                // 跨域的地址，注意 127.0.0.1 != localhost
//                .allowedOrigins("*")
//                // 跨域的请求头
//                .allowedHeaders("*")
//                // 跨域的请求方法
//                .allowedMethods("*")
//                // 跨域是否支持证书
//                .allowCredentials(true);
//    }
//}
