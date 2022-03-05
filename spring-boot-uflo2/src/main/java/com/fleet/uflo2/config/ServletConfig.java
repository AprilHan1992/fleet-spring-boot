package com.fleet.uflo2.config;


import com.bstek.uflo.console.UfloServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean servletRegistration() {
        return new ServletRegistrationBean(new UfloServlet(), "/uflo/*");
    }
}
