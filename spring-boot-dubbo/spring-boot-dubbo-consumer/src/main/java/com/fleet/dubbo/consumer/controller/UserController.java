package com.fleet.dubbo.consumer.controller;

import com.fleet.dubbo.common.entity.User;
import com.fleet.dubbo.common.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody User user) {
        userService.insert(user);
        return "成功";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id) {
        userService.delete(id);
        return "成功";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody User user) {
        userService.update(user);
        return "成功";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public User get(@RequestParam("id") Long id) {
        return userService.get(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> list(@RequestParam Map<String, Object> map) {
        return userService.list(map);
    }
}
