package com.fleet.feign.consumer.controller;

import com.fleet.feign.consumer.feign.ProviderFeignClient1;
import com.fleet.feign.consumer.feign.ProviderFeignClient2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@RestController
public class HelloController {

    @Resource
    ProviderFeignClient1 providerFeignClient1;

    @Resource
    ProviderFeignClient2 providerFeignClient2;

    @RequestMapping("/hello1")
    public String hello1() {
        return providerFeignClient1.hello();
    }

    @RequestMapping("/hello2")
    public String hello2() {
        return providerFeignClient2.hello();
    }
}
