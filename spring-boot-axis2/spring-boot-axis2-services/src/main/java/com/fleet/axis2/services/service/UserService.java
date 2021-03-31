package com.fleet.axis2.services.service;

import com.fleet.axis2.services.entity.User;

import javax.jws.WebParam;

/**
 * @author April Han
 */
public interface UserService {

    User get(@WebParam(name = "id") Long id);

    String getName(@WebParam(name = "id") Long id);
}
