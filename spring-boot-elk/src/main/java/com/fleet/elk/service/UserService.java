package com.fleet.elk.service;

import com.fleet.elk.entity.User;

import java.util.List;

public interface UserService {

    Long insert(User user);

    List<User> list(Integer pageNumber, Integer pageSize, String searchContent);
}
