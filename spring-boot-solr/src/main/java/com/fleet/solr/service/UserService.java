package com.fleet.solr.service;

import com.fleet.solr.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
public interface UserService {

    void insert(User user);

    void delete(String id);

    void deletes();

    void update(User user);

    User get(String id);

    List<User> list(Map<String, String> map);
}
