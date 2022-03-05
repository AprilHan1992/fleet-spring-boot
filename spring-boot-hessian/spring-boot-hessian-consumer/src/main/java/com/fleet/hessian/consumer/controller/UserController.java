package com.fleet.hessian.consumer.controller;

import com.fleet.hessian.provider.entity.User;
import com.fleet.hessian.provider.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/insert")
    public Boolean insert(@RequestBody User user) {
        return userService.insert(user);
    }

    @RequestMapping("/delete")
    public Boolean delete(Long id) {
        return userService.delete(id);
    }

    @RequestMapping("/update")
    public Boolean update(@RequestBody User user) {
        return userService.update(user);
    }

    @RequestMapping("/get")
    public User get(Long id) {
        return userService.get(id);
    }

    @RequestMapping("/list")
    public List<User> list(Map<String, Object> map) {
        return userService.list(map);
    }
}
