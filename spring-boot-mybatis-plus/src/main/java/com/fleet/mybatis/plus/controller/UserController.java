package com.fleet.mybatis.plus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fleet.mybatis.plus.entity.User;
import com.fleet.mybatis.plus.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/{id}")
    public User update(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateById(user);
        return user;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/listPage")
    public Page<User> listPage(@RequestParam Map<String, Object> map,
                               @RequestParam(value = "current", defaultValue = "1") int current,
                               @RequestParam(value = "size", defaultValue = "10") int size) {
        return userService.listPage(new Page<>(current, size), map);
    }
}
