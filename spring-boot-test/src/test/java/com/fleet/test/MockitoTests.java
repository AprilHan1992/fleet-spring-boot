package com.fleet.test;

import com.alibaba.fastjson.JSON;
import com.fleet.test.dao.UserDao;
import com.fleet.test.entity.User;
import com.fleet.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockitoTests {

    @Resource
    private UserService userService;

    /**
     * 使用 Mockito 去模拟一个假的 userDao，模拟数据
     */
    @MockBean
    private UserDao userDao;

    @Test
    public void insertUser() {
        Mockito.when(userDao.insert(Mockito.any(User.class))).thenReturn(1);
        System.out.println(userService.insert(new User(1L, "fleet")));
        System.out.println(userService.insert(new User(2L, "fleet")));
    }

    @Test
    public void getUser() {
        Mockito.when(userDao.get(1L)).thenReturn(new User(1L, "fleet"));
        System.out.println(JSON.toJSONString(userService.get(1L)));
        System.out.println(JSON.toJSONString(userService.get(2L)));
    }
}
