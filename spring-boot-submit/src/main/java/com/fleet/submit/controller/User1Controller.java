package com.fleet.submit.controller;

import com.alibaba.fastjson.JSON;
import com.fleet.submit.entity.User;
import com.fleet.submit.config.handler.BaseException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap
 */
@RestController
@RequestMapping("/user1")
public class User1Controller {

    /**
     * HashMap 是无限增长的，因此它会占用越来越多的内存，并且随着 HashMap 数量的增加查找的速度也会降低
     */
    private Map<Long, String> map = new HashMap<>();

    @RequestMapping("/insert")
    public String insert(@RequestBody User user) {
        synchronized (this) {
            if (map.containsKey(user.getId())) {
                throw new BaseException("重复提交");
            }
            map.put(user.getId(), user.getName());
        }
        return JSON.toJSONString(user);
    }
}
