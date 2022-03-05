package com.fleet.tkmybatis.service;

import com.fleet.tkmybatis.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    int insert(User user);

    int delete(Long id);

    int update(User user);

    User get(Long id);

    List<User> list();
}
