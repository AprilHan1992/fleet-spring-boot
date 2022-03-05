package com.fleet.feign.consumer.feign;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign 不使用 Eureka
 *
 * @author April Han
 */
@FeignClient(value = "provider", url = "http://127.0.0.1:8001", fallbackFactory = ProviderFeign2FallbackFactory.class)
public interface ProviderFeignClient2 {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello();
}

@Component
class ProviderFeign2FallbackFactory implements FallbackFactory<ProviderFeignClient2> {

    private static final Logger logger = LoggerFactory.getLogger(ProviderFeign2FallbackFactory.class);

    @Override
    public ProviderFeignClient2 create(Throwable t) {

        return new ProviderFeignClient2() {

            @Override
            public String hello() {
                logger.info("Fallback error, Caused by:", t);
                return "Hi";
            }
        };
    }
}
