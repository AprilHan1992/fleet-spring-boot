package com.fleet.filter.config.filter;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author April Han
 * @Order 注解 或 继承 Ordered 接口，不能指定 Filter 的优先级
 */
@WebFilter
public class Req2Filter implements Filter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(Req2Filter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        logger.info("url={}, params={}", req.getRequestURI(), JSON.toJSONString(req.getParameterMap()));
        chain.doFilter(req, response);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
