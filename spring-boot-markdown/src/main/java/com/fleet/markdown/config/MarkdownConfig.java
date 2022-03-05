package com.fleet.markdown.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ViewResolver;

@Component
@Configuration
public class MarkdownConfig {

    @Bean
    public ViewResolver viewResolver() {
        MarkdownViewResolver viewResolver = new MarkdownViewResolver();
        viewResolver.setSuffix(".md");
        return viewResolver;
    }
}
