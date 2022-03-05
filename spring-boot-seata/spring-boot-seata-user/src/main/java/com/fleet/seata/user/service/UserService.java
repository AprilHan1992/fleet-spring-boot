package com.fleet.seata.user.service;

import com.fleet.seata.user.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
public interface UserService {

    User get(Integer id);

    boolean pay(Integer id, Integer money);
}
