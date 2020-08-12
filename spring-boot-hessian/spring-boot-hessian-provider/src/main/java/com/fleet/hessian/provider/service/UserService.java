package com.fleet.hessian.provider.service;

import com.fleet.hessian.provider.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
public interface UserService {

    Boolean insert(User user);

    Boolean delete(Long id);

    Boolean update(User user);

    User get(Long id);

    List<User> list(Map<String, Object> map);
}
