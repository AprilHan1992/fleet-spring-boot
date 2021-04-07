package com.fleet.axis2.services.service;

import com.fleet.axis2.services.entity.User;

/**
 * @author April Han
 */
public interface UserService {

    User get(Long id);

    String getName(Long id);
}
