package com.fleet.ws.services.service;

import com.fleet.ws.services.entity.User;

/**
 * @author April Han
 */
public interface UserService {

    User get(Long id);

    String getName(Long id);
}
