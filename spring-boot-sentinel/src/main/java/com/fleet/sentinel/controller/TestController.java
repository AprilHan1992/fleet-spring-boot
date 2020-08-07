package com.fleet.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/get")
//    @SentinelResource(value = "get", fallback = "getFallback")
    public Map<String, Object> get() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("info", "成功");
        return map;
    }

    /**
     * 该方法降级处理函数，参数要与原函数 get() 相同，并且返回值类型也要与原函数相同，此外，该方法必须与原函数在同一个类中
     */
    public Map<String, Object> getFallback() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("info", "失败");
        return map;
    }
}
