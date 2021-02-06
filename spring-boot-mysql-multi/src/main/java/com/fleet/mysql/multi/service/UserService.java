package com.fleet.mysql.multi.service;

import com.fleet.mysql.multi.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
public interface UserService {

    int insert(User user);

    int delete(Long id);

    int update(User user);

    User get(Long id);

    List<User> list(Map<String, Object> map);
}
