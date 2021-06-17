package com.fleet.mybatis.pagehelper;

import com.fleet.mybatis.pagehelper.entity.User;
import com.fleet.mybatis.pagehelper.page.PageUtil;
import com.fleet.mybatis.pagehelper.page.entity.Page;
import com.fleet.mybatis.pagehelper.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPageHelperApplicationTests {

    @Resource
    UserService userService;

    @Test
    public void listPage() {
        Page page = new Page(3, 5);
        PageUtil<User> pageUtil = userService.listPage(page);
        System.out.println(pageUtil);
    }
}
