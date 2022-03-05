package com.fleet.jpush;


import com.fleet.jpush.entity.JPush;
import com.fleet.jpush.service.JPushService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpushApplicationTests {

    @Autowired
    JPushService jPushService;

    @Test
    public void push() {
        JPush jPush = new JPush();
        jPush.setAlert("测试");
        jPush.setTitle("这是测试");
        jPushService.push(jPush, "170976fa8aee6739f7e");
    }
}
