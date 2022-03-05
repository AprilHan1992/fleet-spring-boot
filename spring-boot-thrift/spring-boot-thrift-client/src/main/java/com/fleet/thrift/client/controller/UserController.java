package com.fleet.thrift.client.controller;

import com.fleet.thrift.client.client.ThriftClient;
import com.fleet.thrift.common.gen.User;
import org.apache.thrift.TException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private ThriftClient thriftClient;

    @RequestMapping("/insert")
    public void insert(@RequestBody User user) throws TException {
        thriftClient.getClient().insert(user);
    }

    @RequestMapping("/get")
    public User get(int id) throws TException {
        return thriftClient.getClient().get(id);
    }
}
