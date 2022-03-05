package com.fleet.sqlite.service;

import com.fleet.sqlite.entity.User;

import java.util.List;

public interface UserService {

    int insert(User user);

    int delete(Long id);

    int update(User user);

    User get(Long id);

    List<User> list();
}
