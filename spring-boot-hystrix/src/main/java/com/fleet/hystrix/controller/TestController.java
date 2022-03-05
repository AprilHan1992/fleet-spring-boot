package com.fleet.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/get")
    @HystrixCommand(fallbackMethod = "getFallback")
    public Map<String, Object> get() throws InterruptedException {
        Thread.sleep(2000);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("info", "成功");
        return map;
    }

    public Map<String, Object> getFallback() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("info", "失败");
        return map;
    }
}
