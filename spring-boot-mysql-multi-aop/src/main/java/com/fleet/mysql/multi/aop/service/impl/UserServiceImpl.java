package com.fleet.mysql.multi.aop.service.impl;

import com.fleet.mysql.multi.aop.annotation.Master;
import com.fleet.mysql.multi.aop.dao.UserDao;
import com.fleet.mysql.multi.aop.entity.User;
import com.fleet.mysql.multi.aop.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Master
    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Master
    @Override
    public int delete(Long id) {
        return userDao.delete(id);
    }

    @Master
    @Override
    public int update(User user) {
        return userDao.update(user);
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
