package com.fleet.redis.controller;

import com.fleet.redis.util.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    RedisUtil redisUtil;

    @RequestMapping("/set")
    public void set(@RequestParam("key") String key, @RequestParam("value") Object value) {
        redisUtil.set(key, value);
    }

    @RequestMapping("/delete")
    public void delete(String key) {
        redisUtil.delete(key);
    }

    @RequestMapping("/get")
    public Object get(String key) {
        return redisUtil.get(key);
    }
}
