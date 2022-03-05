package com.fleet.clickhouse.service;

import com.fleet.clickhouse.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
public interface UserService {

    int insert(User user);

    User get(Long id);

    List<User> list(Map<String, Object> map);
}
