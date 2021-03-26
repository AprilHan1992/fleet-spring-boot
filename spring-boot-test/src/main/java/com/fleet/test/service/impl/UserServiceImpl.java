package com.fleet.test.service.impl;

import com.fleet.test.dao.UserDao;
import com.fleet.test.entity.User;
import com.fleet.test.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public boolean insert(User user) {
        return userDao.insert(user) != 0;
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }
}
