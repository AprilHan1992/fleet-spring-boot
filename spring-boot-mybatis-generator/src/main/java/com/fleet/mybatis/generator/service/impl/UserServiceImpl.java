package com.fleet.mybatis.generator.service.impl;

import com.fleet.mybatis.generator.dao.UserMapper;
import com.fleet.mybatis.generator.entity.User;
import com.fleet.mybatis.generator.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Boolean insert(User user) {
        if (userMapper.insert(user) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        if (userMapper.deleteByPrimaryKey(id) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(User user) {
        if (userMapper.updateByPrimaryKeySelective(user) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
