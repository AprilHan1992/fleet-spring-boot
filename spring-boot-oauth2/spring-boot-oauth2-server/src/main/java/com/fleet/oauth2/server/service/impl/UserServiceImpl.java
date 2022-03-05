package com.fleet.oauth2.server.service.impl;

import com.fleet.oauth2.server.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author April Han
 */
@Component
@Service
public class UserServiceImpl implements UserService {

    /**
     * 模拟用户
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = new BCryptPasswordEncoder().encode("123456");
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        // 放入角色项，需要加前缀 ROLE_ ，hasRole 校验时可以不加 ROLE_ 前缀
        // 放入权限项，hasAuthority 校验时必须为权限项全字符串
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("user".equals(username)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            authorities.add(new SimpleGrantedAuthority("USER:GET"));
        } else if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("USER:GET"));
        } else {
            return null;
        }

        return new User(username, password, authorities);
    }
}
