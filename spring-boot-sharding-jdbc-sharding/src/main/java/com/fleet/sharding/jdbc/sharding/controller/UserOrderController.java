package com.fleet.sharding.jdbc.sharding.controller;

import com.fleet.sharding.jdbc.sharding.entity.UserOrder;
import com.fleet.sharding.jdbc.sharding.repository.UserOrderRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/order")
public class UserOrderController {

    @Resource
    private UserOrderRepository userOrderRepository;

    @RequestMapping("/insert")
    public void insert(@RequestBody UserOrder userOrder) {
        userOrderRepository.save(userOrder);
    }

    @RequestMapping("/list")
    public Iterable<UserOrder> list() {
        return userOrderRepository.findAll();
    }
}
