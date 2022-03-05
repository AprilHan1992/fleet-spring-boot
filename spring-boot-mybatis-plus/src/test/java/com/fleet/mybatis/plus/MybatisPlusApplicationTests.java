package com.fleet.mybatis.plus;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fleet.mybatis.plus.entity.User;
import com.fleet.mybatis.plus.enums.SexEnum;
import com.fleet.mybatis.plus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {

    @Resource
    UserService userService;

    @Test
    public void save() {
        User user = new User("fleet");
        System.out.println(userService.save(user));
    }

    @Test
    public void saveBatch() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("fleet"));
        System.out.println(userService.saveBatch(userList));
    }

    @Test
    public void removeById() {
        System.out.println(userService.removeById(1L));
    }

    @Test
    public void removeByIds() {
        System.out.println(userService.removeByIds(Arrays.asList(1L, 2L, 3L)));
    }

    @Test
    public void removeByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "fleet");
        System.out.println(userService.removeByMap(map));
    }

    @Test
    public void remove() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "fleet");
        System.out.println(userService.remove(queryWrapper));
    }

    @Test
    public void deleteUserByName() {
        System.out.println(userService.deleteByName("fleet"));
    }

    @Test
    public void saveOrUpdate() {
        User user = new User(1L, "fleet");
        System.out.println(userService.saveOrUpdate(user));
    }

    @Test
    public void update1() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("name", "fleet");
        updateWrapper.set("sex", 1);
        updateWrapper.eq("id", 1L);
        System.out.println(userService.update(updateWrapper));
    }

    @Test
    public void update2() {
        User user = new User("fleet");
        user.setSex(SexEnum.MAN);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 1L);
        System.out.println(userService.update(user, updateWrapper));
    }

    @Test
    public void updateById() {
        User user = new User(1L, "fleet");
        System.out.println(userService.updateById(user));
    }

    @Test
    public void getById() {
        User user = userService.getById(1L);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void getOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 1L);
        User user = userService.getOne(queryWrapper);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void listPage() {
        Page<User> page = new Page<>(2, 1);
        Map<String, Object> map = new HashMap<>();
        Page<User> listPage = userService.listPage(page, map);
        System.out.println(listPage);
    }


//    @Test
//    public void getNameById() {
//        String name = userService.getNameById(1L);
//        System.out.println(name);
//    }
//
//    @Test
//    public void list() {
//        List<User> userList = userService.list();
//        System.out.println(JSON.toJSONString(userList));
//    }
//
//    @Test
//    public void listPage() {
//        Page page = new Page(1, 5);
//        PageUtil<User> pageUtil = userService.listPage(null, page);
//        System.out.println(JSON.toJSONString(pageUtil));
//    }
}
