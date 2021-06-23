package com.fleet.mybatis.dynamic.sql.controller;

import com.fleet.mybatis.dynamic.sql.entity.User;
import com.fleet.mybatis.dynamic.sql.page.PageUtil;
import com.fleet.mybatis.dynamic.sql.page.entity.Page;
import com.fleet.mybatis.dynamic.sql.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/insert")
    public void insert(@RequestBody User user) {
        userService.insert(user);
    }

    @RequestMapping("/delete")
    public void delete(Integer id) {
        userService.delete(id);
    }

    @RequestMapping("/update")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping("/get")
    public User get(Integer id) {
        return userService.get(id);
    }

    @RequestMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @RequestMapping("/listPage")
    public PageUtil<User> listPage(@RequestParam Map<String, Object> map,
                                   @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                   @RequestParam(value = "pageRows", required = false, defaultValue = "20") int pageRows) {
        Page page = new Page(pageIndex, pageRows);
        return userService.listPage(map, page);
    }
}
