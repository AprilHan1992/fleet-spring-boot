package com.fleet.japidocs.controller;

import com.fleet.japidocs.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return boolean
     */
    @PostMapping("/insert")
    public boolean insert(@RequestBody User user) {
        return true;
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return boolean
     */
    @GetMapping("/delete")
    public boolean delete(@RequestParam Long id) {
        return true;
    }

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return boolean
     */
    @PostMapping("/update")
    public boolean update(@RequestBody User user) {
        return true;
    }

    /**
     * 查询用户
     *
     * @param id 用户id
     * @return User
     */
    @GetMapping("/get")
    public User get(@RequestParam Long id) {
        User user = new User();
        user.setId(1L);
        user.setName("fleet");
        return user;
    }
}
