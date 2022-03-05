package com.fleet.ribbon.feign;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "service-provider", fallbackFactory = ProviderFeignFallbackFactory.class)
public interface ProviderFeignClient {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello();
}

@Component
class ProviderFeignFallbackFactory implements FallbackFactory<ProviderFeignClient> {

    private static final Logger logger = LoggerFactory.getLogger(ProviderFeignFallbackFactory.class);

    @Override
    public ProviderFeignClient create(Throwable t) {

        return new ProviderFeignClient() {

            @Override
            public String hello() {
                logger.info("Fallback error, Caused by:", t);
                return "Hi";
            }
        };
    }
}
