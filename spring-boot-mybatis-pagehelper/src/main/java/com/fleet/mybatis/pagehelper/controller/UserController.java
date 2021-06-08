package com.fleet.mybatis.pagehelper.controller;

import com.fleet.mybatis.pagehelper.page.entity.Page;
import com.fleet.mybatis.pagehelper.page.PageUtil;
import com.fleet.mybatis.pagehelper.entity.User;
import com.fleet.mybatis.pagehelper.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list() {
        return userService.list(new HashMap<>());
    }

    @RequestMapping("/listPage")
    public PageUtil<User> listPage(@RequestBody Page page) {
        return userService.listPage(page);
    }
}
