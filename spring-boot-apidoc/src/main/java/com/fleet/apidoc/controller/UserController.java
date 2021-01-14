package com.fleet.apidoc.controller;

import com.fleet.apidoc.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * @apiDefine group 用户信息
     */

    /**
     * @api {post} user/insert 添加用户信息
     * @apiVersion 1.0.0
     * @apiGroup group
     * @apiName 1、添加用户信息
     * @apiDescription 根据User对象添加用户信息
     * @apiHeader {String} Content-Type="application/json" Content-Type类型
     * @apiParam {String} json User对象
     * @apiParamExample {json} Request-Example:
     * { "name": "fleet" }
     * @apiSuccessExample Success-Response:
     * true
     * @apiErrorExample Error-Response:
     * false
     */
    @CrossOrigin
    @RequestMapping(value = "/insert")
    public boolean insert(@RequestBody User user) {
        return true;
    }

    /**
     * @api {get} user/delete 根据用户id删除用户信息
     * @apiVersion 1.0.0
     * @apiGroup group
     * @apiName 2、根据用户id删除用户信息
     * @apiDescription 根据用户id删除用户信息
     * @apiParam {Long} id 用户id
     * @apiSuccessExample Success-Response:
     * true
     * @apiErrorExample Error-Response:
     * false
     * @apiSampleRequest user/delete
     */
    @CrossOrigin
    @RequestMapping(value = "/delete")
    public boolean delete(@RequestParam Long id) {
        return true;
    }

    /**
     * @api {post} user/update 修改用户信息
     * @apiVersion 1.0.0
     * @apiGroup group
     * @apiName 3、修改用户信息
     * @apiDescription 根据User对象修改用户信息
     * @apiHeader {String} Content-Type="application/json" Content-Type
     * @apiParam {String} json User对象
     * @apiParamExample {json} Request-Example:
     * { "id": 1, "name": "fleet" }
     * @apiSuccessExample Success-Response:
     * true
     * @apiErrorExample Error-Response:
     * false
     */
    @CrossOrigin
    @RequestMapping(value = "/update")
    public boolean update(@RequestBody User user) {
        return true;
    }

    /**
     * @api {get} user/get 根据用户id查询用户信息
     * @apiVersion 1.0.0
     * @apiGroup group
     * @apiName 4、根据用户id查询用户信息
     * @apiDescription 根据用户id查询用户信息
     * @apiParam {Long} id 用户id
     * @apiSuccess {Long} id 用户id
     * @apiSuccess {String} name 用户名
     * @apiSuccessExample Success-Response:
     * { "id": 1, "name": "fleet" }
     * @apiSampleRequest user/get
     */
    @CrossOrigin
    @RequestMapping(value = "/get")
    public User get(@RequestParam Long id) {
        User user = new User();
        user.setId(1L);
        user.setName("fleet");
        return user;
    }
}
