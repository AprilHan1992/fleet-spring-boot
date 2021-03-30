package com.fleet.narayana.service;

import com.fleet.narayana.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
public interface UserService {

    User get(Integer id);

    /**
     * 支付
     *
     * @param id
     * @param money
     * @return
     */
    boolean pay(Integer id, Integer money);
}
