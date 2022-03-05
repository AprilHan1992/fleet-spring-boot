package com.fleet.jooq.service;


import com.fleet.jooq.generator.tables.pojos.User;

import java.util.List;

public interface UserService {

    void insert(User user);

    void delete(Integer id);

    void update(User user);

    User get(Integer id);

    List<User> list();
}
