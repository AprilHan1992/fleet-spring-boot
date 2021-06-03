package com.fleet.test;

import com.alibaba.fastjson.JSON;
import com.fleet.test.entity.User;
import com.fleet.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @Resource
    private UserService userService;

    @Test
    public void insertUser() {
        User user = new User(1L, "fleet");
        System.out.println(userService.insert(user));
    }

    @Test
    public void getUser() {
        User user = userService.get(1L);
        System.out.println(JSON.toJSONString(user));
    }
}
