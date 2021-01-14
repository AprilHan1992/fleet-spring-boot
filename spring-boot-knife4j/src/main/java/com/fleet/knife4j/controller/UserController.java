package com.fleet.knife4j.controller;

import com.fleet.knife4j.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户相关api")
@RestController
public class UserController {

    @ApiOperation("添加用户信息")
    @ApiImplicitParam(name = "user", value = "User对象", required = true)
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public boolean insert(@RequestBody User user) {
        return true;
    }

    @ApiOperation("根据用户id删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public boolean delete(@RequestParam Long id) {
        return true;
    }

    @ApiOperation("修改用户信息")
    @ApiImplicitParam(name = "user", value = "User对象", required = true)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public boolean update(@RequestBody User user) {
        return true;
    }

    @ApiOperation("根据用户id查询用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public User get(@RequestParam Long id) {
        User user = new User();
        user.setId(1L);
        user.setName("fleet");
        return user;
    }
}
