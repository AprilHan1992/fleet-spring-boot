package com.fleet.axis2.service;

import com.fleet.axis2.entity.User;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author April Han
 */
@WebService(serviceName = "userService", targetNamespace = "http://service.axis2.fleet.com")
public interface UserService {

    User get(@WebParam(name = "id") Long id);

    String getName(@WebParam(name = "id") Long id);
}
