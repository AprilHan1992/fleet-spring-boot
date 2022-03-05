package com.fleet.jasypt.service;

import com.fleet.jasypt.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author April Han
 */
@Component
public interface UserService {

    int insert(User user);

    int delete(Long id);

    int[] deletes(Long[] ids);

    int update(User user);

    User get(Long id);

    List<User> list();

    int count();
}
