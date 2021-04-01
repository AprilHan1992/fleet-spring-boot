package com.fleet.mybatis.generator.service;

import com.fleet.mybatis.generator.entity.User;

/**
 * @author April Han
 */
public interface UserService {

    Boolean insert(User user);

    Boolean delete(Integer id);

    Boolean update(User user);

    User get(Integer id);
}
