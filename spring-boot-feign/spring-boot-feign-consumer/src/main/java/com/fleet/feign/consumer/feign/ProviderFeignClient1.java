package com.fleet.feign.consumer.feign;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author April Han
 */
@FeignClient(name = "feign-provider", fallbackFactory = ProviderFeign1FallbackFactory.class)
public interface ProviderFeignClient1 {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello();
}

@Component
class ProviderFeign1FallbackFactory implements FallbackFactory<ProviderFeignClient1> {

    private static final Logger logger = LoggerFactory.getLogger(ProviderFeign1FallbackFactory.class);

    @Override
    public ProviderFeignClient1 create(Throwable t) {

        return new ProviderFeignClient1() {

            @Override
            public String hello() {
                logger.info("Fallback error, Caused by:", t);
                return "Hi";
            }
        };
    }
}
