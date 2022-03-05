package com.fleet.sqlite.service.impl;

import com.fleet.sqlite.dao.UserDao;
import com.fleet.sqlite.entity.User;
import com.fleet.sqlite.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }
}
