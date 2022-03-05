package com.fleet.elasticsearch.controller;

import com.fleet.elasticsearch.entity.User;
import com.fleet.elasticsearch.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Long insert(@RequestBody User user) {
        return userService.insert(user);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<User> list(@RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "searchContent") String searchContent) {
        return userService.list(pageNumber, pageSize, searchContent);
    }
}
