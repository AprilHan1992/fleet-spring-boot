package com.fleet.lombok;

import com.alibaba.fastjson.JSON;
import com.fleet.lombok.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LombokApplicationTests {

    @Test
    public void contextLoads() {
        User user = new User();
        user.setId(1L);
        user.setName("张三");
        System.err.println(JSON.toJSONString(user));
    }
}
