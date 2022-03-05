package com.fleet.mycat.mysql.sharding.service;

import com.fleet.mycat.mysql.sharding.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    int insert(User user);

    int delete(Long id);

    int update(User user);

    User get(Long id);

    List<User> list(Map<String, Object> map);
}
