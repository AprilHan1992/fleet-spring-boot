package com.fleet.ribbon.controller;

import com.fleet.ribbon.feign.ProviderFeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    ProviderFeignClient providerFeignClient;

    @RequestMapping("/hello")
    public String hello() {
        return providerFeignClient.hello();
    }
}
