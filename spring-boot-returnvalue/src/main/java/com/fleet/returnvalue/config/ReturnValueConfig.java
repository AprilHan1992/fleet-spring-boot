package com.fleet.returnvalue.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author April Han
 */
@Configuration
public class ReturnValueConfig implements InitializingBean {

    @Resource
    RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> origHandlers = adapter.getReturnValueHandlers();
        if (origHandlers != null) {
            List<HandlerMethodReturnValueHandler> newHandlers = new ArrayList<>();
            for (HandlerMethodReturnValueHandler origHandler : origHandlers) {
                if (origHandler instanceof RequestResponseBodyMethodProcessor) {
                    newHandlers.add(new ReturnValueHandler(origHandler));
                } else {
                    newHandlers.add(origHandler);
                }
            }
            adapter.setReturnValueHandlers(newHandlers);
        }
    }
}
