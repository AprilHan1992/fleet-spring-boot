package com.fleet.axis2.services.service.impl;

import com.fleet.axis2.services.entity.User;
import com.fleet.axis2.services.service.UserService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

/**
 * @author April Han
 */
@Service
@WebService
public class UserServiceImpl implements UserService {

    private Map<Long, User> users = new HashMap<>();

    public UserServiceImpl() {
        User user = new User();
        user.setId(1L);
        user.setName("fleet-1");
        users.put(user.getId(), user);

        user = new User();
        user.setId(2L);
        user.setName("fleet-2");
        users.put(user.getId(), user);

        user = new User();
        user.setId(3L);
        user.setName("fleet-3");
        users.put(user.getId(), user);
    }

    @Override
    public User get(Long id) {
        return users.get(id);
    }

    @Override
    public String getName(Long id) {
        return users.get(id).getName();
    }
}
