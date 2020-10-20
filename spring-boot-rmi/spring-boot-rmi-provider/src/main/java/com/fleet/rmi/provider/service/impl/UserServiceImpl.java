package com.fleet.rmi.provider.service.impl;

import com.fleet.rmi.common.entity.User;
import com.fleet.rmi.common.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public Boolean insert(User user) {
        return true;
    }

    @Override
    public Boolean delete(Long id) {
        return true;
    }

    @Override
    public Boolean update(User user) {
        return true;
    }

    @Override
    public User get(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("fleet");
        return user;
    }

    @Override
    public List<User> list(Map<String, Object> map) {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setName("fleet");
        userList.add(user);
        return userList;
    }
}
