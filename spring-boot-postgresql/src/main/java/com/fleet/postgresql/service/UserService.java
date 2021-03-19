package com.fleet.postgresql.service;

import com.fleet.postgresql.entity.User;

/**
 * @author April Han
 */
public interface UserService {

    int insert(User user);

    User get(Long id);
}
