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
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPageHelperApplicationTests {

    @Resource
    UserService userService;

    @Test
    public void listPage() {
        Page page = new Page(1, 5);
        Map<String, Object> map = new HashMap<>();
        PageUtil<User> pageUtil = userService.listPage(map, page);
        System.out.println(pageUtil);
    }
}
