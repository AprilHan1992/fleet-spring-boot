package com.fleet.enjoy.config;

import com.jfinal.template.ext.spring.JFinalViewResolver;
import com.jfinal.template.source.ClassPathSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnjoyConfig {

    @Bean
    public JFinalViewResolver jFinalViewResolver() {
        JFinalViewResolver jf = new JFinalViewResolver();
        jf.setDevMode(true);
        jf.setSessionInView(true);
        jf.setSourceFactory(new ClassPathSourceFactory());
        jf.setBaseTemplatePath("/templates/");
        jf.setSuffix(".html");
        jf.setContentType("text/html;charset=UTF-8");
        // 指定默认的日期格式化样式
        jf.setDatePattern("yyyy-MM-dd HH:mm:ss");
        jf.setOrder(0);
        return jf;
    }
}
