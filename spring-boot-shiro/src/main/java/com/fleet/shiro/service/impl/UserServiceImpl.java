package com.fleet.shiro.service.impl;

import com.fleet.shiro.entity.User;
import com.fleet.shiro.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author April Han
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 模拟用户
     *
     * @param username 用户名
     * @return
     */
    @Override
    public User getByUsername(String username) {
        if ("user".equals(username)) {
            User user = new User();
            user.setId(1L);
            user.setUsername("user");
            user.setPassword("123456");
            user.setStatus(1);

            Set<String> roles = new HashSet<>();
            roles.add("USER");
            user.setRoles(roles);

            Set<String> permissions = new HashSet<>();
            permissions.add("USER:GET");
            user.setPermissions(permissions);

            return user;
        } else if ("admin".equals(username)) {
            User user = new User();
            user.setId(2L);
            user.setUsername("admin");
            user.setPassword("123456");
            user.setStatus(1);

            Set<String> roles = new HashSet<>();
            roles.add("ADMIN");
            user.setRoles(roles);

            return user;
        } else {
            return null;
        }
    }
}
