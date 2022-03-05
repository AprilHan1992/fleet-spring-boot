package com.fleet.filter.config;

import com.fleet.filter.config.filter.ReqFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

/**
 * 方式二
 * 利用 bean 注册方式
 *
 * @author April Han
 */
@Configuration
public class FilterConfig {

    /**
     * 通过创建 FilterRegistrationBean 指定优先级
     */
    @Bean
    public FilterRegistrationBean<ReqFilter> reqFilterRegistrationBean() {
        FilterRegistrationBean<ReqFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new ReqFilter());
        // 通过创建 FilterRegistrationBean 的时候指定优先级，值越小，Filter 越靠前
        filterRegistrationBean.setOrder(0);
        filterRegistrationBean.setName("req3Filter");
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistrationBean;
    }
}
