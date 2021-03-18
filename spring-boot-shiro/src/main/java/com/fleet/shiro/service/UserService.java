package com.fleet.shiro.service;

import com.fleet.shiro.entity.User;

/**
 * @author April Han
 */
public interface UserService {

    User getByUsername(String username);
}
