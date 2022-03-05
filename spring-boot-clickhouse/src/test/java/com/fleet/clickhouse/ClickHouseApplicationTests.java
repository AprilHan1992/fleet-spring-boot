package com.fleet.clickhouse;

import com.fleet.clickhouse.entity.User;
import com.fleet.clickhouse.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClickHouseApplicationTests {

    @Resource
    UserService userService;

    @Test
    public void insert() {
        User user = new User(1L, "fleet");
        userService.insert(user);
    }
}
