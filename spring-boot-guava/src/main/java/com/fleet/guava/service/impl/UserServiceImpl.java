package com.fleet.guava.service.impl;

import com.fleet.guava.dao.UserDao;
import com.fleet.guava.entity.User;
import com.fleet.guava.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = {"users"})
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    @CachePut(key = "#user.id")
    public User insert(User user) {
        userDao.insert(user);
        return user;
    }

    @Override
    @CacheEvict(key = "#id")
    public int delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    @CacheEvict(key = "#user.id")
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    @Cacheable(key = "#id")
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public List<User> list(Map<String, Object> map) {
        return userDao.list(map);
    }
}
