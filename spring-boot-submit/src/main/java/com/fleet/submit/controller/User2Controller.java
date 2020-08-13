package com.fleet.submit.controller;

import com.alibaba.fastjson.JSON;
import com.fleet.submit.entity.User;
import com.fleet.submit.config.handler.BaseException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 固定大小的数组
 */
@RestController
@RequestMapping("/user2")
public class User2Controller {

    private static Long[] ids = new Long[100];

    private static Integer counter = 0;

    @RequestMapping("/insert")
    public String insert(@RequestBody User user) {
        synchronized (this) {
            if (Arrays.asList(ids).contains(user.getId())) {
                throw new BaseException("重复提交");
            }
            if (counter >= ids.length) {
                // 重置计数器
                counter = 0;
            }
            ids[counter] = user.getId();
            counter++;
        }
        return JSON.toJSONString(user);
    }
}
