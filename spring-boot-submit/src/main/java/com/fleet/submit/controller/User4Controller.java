package com.fleet.submit.controller;

import com.alibaba.fastjson.JSON;
import com.fleet.submit.entity.User;
import com.fleet.submit.config.handler.BaseException;
import org.apache.commons.collections4.map.LRUMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 双重检测锁(DCL)
 */
@RestController
@RequestMapping("/user4")
public class User4Controller {

    private LRUMap<Long, String> map = new LRUMap<>(100);

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
