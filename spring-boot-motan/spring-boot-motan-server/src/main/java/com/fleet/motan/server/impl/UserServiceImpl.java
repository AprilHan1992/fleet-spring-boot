package com.fleet.motan.server.impl;

import com.fleet.motan.common.entity.User;
import com.fleet.motan.common.service.UserService;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@MotanService
public class UserServiceImpl implements UserService {

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
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
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setName("fleet");
        list.add(user);
        return list;
    }
}
