package com.fleet.seata.user.controller;

import com.fleet.seata.user.entity.User;
import com.fleet.seata.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/get")
    public User get(@RequestParam Integer id) {
        return userService.get(id);
    }

    /**
     * 支付
     *
     * @param id
     * @param money
     * @return
     */
    @RequestMapping("/pay")
    public boolean pay(@RequestParam("id") Integer id, @RequestParam("money") Integer money) {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("支付失败");
        }
        return userService.pay(id, money);
    }
}
