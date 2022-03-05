package com.fleet.solr.controller;

import com.fleet.solr.entity.User;
import com.fleet.solr.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    UserService userService;

    @RequestMapping("/insert")
    public void insert(@RequestBody User user) {
        userService.insert(user);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam("id") String id) {
        userService.delete(id);
    }

    @RequestMapping("/update")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping("/get")
    public User get(@RequestParam("id") String id) {
        return userService.get(id);
    }

    @RequestMapping("/list")
    public List<User> list(@RequestParam Map<String, String> map) {
        return userService.list(map);
    }
}
