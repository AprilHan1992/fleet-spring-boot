package com.fleet.mybatis.pagehelper.controller;

import com.fleet.mybatis.pagehelper.entity.User;
import com.fleet.mybatis.pagehelper.page.PageUtil;
import com.fleet.mybatis.pagehelper.page.PagerUtil;
import com.fleet.mybatis.pagehelper.page.entity.Page;
import com.fleet.mybatis.pagehelper.page.entity.Pager;
import com.fleet.mybatis.pagehelper.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/listPager")
    public PagerUtil<User> listPage(@RequestParam Map<String, Object> map,
                                    @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                    @RequestParam(value = "pageRows", required = false, defaultValue = "20") int pageRows) {
        Pager pager = new Pager(pageIndex, pageRows);
        return userService.listPager(map, pager);
    }
}
