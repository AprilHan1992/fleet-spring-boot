package com.fleet.test.service;

import com.fleet.test.entity.User;

/**
 * @author April Han
 */
public interface UserService {

    boolean insert(User user);

    User get(Long id);
}
