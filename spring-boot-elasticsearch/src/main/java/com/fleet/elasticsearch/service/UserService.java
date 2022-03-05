package com.fleet.elasticsearch.service;

import com.fleet.elasticsearch.entity.User;

import java.util.List;

public interface UserService {

    Long insert(User user);

    List<User> list(Integer pageNumber, Integer pageSize, String searchContent);
}
