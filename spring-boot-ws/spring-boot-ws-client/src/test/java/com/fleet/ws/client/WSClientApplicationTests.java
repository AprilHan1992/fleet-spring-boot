package com.fleet.ws.client;

import com.alibaba.fastjson.JSON;
import com.fleet.ws.client.service.User;
import com.fleet.ws.client.service.UserService;
import com.fleet.ws.client.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WSClientApplicationTests {

    @Test
    public void get() {
        UserService userService = new UserServiceImpl().getUserServicePort();
        User user = userService.get(1L);
        System.out.println("查询到用户:" + JSON.toJSONString(user));
    }

    @Test
    public void getName() {
        UserService userService = new UserServiceImpl().getUserServicePort();
        String name = userService.getName(1L);
        System.out.println("查询到用户名:" + name);
    }
}
