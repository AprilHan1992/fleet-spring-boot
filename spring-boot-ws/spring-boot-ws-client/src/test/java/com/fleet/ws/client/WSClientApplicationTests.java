package com.fleet.ws.client;

import com.alibaba.fastjson.JSON;
import com.fleet.ws.client.config.WsClient;
import com.fleet.ws.client.entity.UserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WSClientApplicationTests {

    @Resource
    private WsClient wsClient;

    @Test
    public void get() throws Exception {
        UserResponse userResponse = wsClient.get(1L);
        System.out.println("查询到用户:" + JSON.toJSONString(userResponse));
    }
}
