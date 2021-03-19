package com.fleet.postgresql.service.impl;

import com.fleet.postgresql.dao.UserDao;
import com.fleet.postgresql.entity.User;
import com.fleet.postgresql.service.UserService;
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
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }
}
