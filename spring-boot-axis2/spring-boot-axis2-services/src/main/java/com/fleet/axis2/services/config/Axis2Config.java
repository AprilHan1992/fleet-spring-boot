package com.fleet.axis2.services.config;

import org.apache.axis2.transport.http.AxisServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author April Han
 */
@Configuration
public class Axis2Config {

    @Bean
    public ServletRegistrationBean<AxisServlet> axisServlet() {
        ServletRegistrationBean<AxisServlet> axisServlet = new ServletRegistrationBean<>(new AxisServlet(), "/services/*");
        String path = this.getClass().getResource("/WEB-INF").getPath();
        axisServlet.addInitParameter("axis2.repository.path", path);
        axisServlet.setLoadOnStartup(1);
        return axisServlet;
    }
}
