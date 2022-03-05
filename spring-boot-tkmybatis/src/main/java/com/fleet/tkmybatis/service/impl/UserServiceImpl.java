package com.fleet.tkmybatis.service.impl;

import com.fleet.tkmybatis.entity.User;
import com.fleet.tkmybatis.mapper.UserMapper;
import com.fleet.tkmybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int delete(Long id) {
        User user = new User();
        user.setId(id);
        return userMapper.delete(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User get(Long id) {
        User user = new User();
        user.setId(id);
        return userMapper.selectOne(user);
    }

    @Override
    public List<User> list() {
        return userMapper.selectAll();
    }
}
