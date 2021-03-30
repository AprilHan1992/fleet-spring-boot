package com.fleet.bitronix.service.impl;

import com.fleet.bitronix.dao.user.UserDao;
import com.fleet.bitronix.entity.User;
import com.fleet.bitronix.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author April Han
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User get(Integer id) {
        return userDao.get(id);
    }

    @Override
    public boolean pay(Integer id, Integer money) {
        if (userDao.pay(id, money) == 0) {
            return false;
        }
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("支付失败");
        }
        return true;
    }
}
