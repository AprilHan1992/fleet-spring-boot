package com.fleet.clickhouse.service.impl;

import com.fleet.clickhouse.dao.UserDao;
import com.fleet.clickhouse.entity.User;
import com.fleet.clickhouse.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<User> list(Map<String, Object> map) {
        return userDao.list(map);
    }
}
