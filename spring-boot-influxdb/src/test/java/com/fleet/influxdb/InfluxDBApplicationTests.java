package com.fleet.influxdb;

import com.alibaba.fastjson.JSON;
import com.fleet.influxdb.entity.User;
import com.fleet.influxdb.util.InfluxDbUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InfluxDBApplicationTests {

    @Resource
    InfluxDbUtils<User> influxDbUtils;

    @Test
    public void add() {
        long regTime = new Date().getTime();
        User user = new User();
        user.setId("1");
        user.setName("fleet");
        user.setRegTime(String.valueOf(regTime));
        influxDbUtils.add(user);
    }

    @Test
    public void list() {
        String sql = "select * from \"user\"";
        List<User> list = influxDbUtils.list(sql, User.class);
        System.out.println(JSON.toJSONString(list));
    }
}
