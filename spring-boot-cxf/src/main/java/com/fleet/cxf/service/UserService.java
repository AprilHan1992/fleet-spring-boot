package com.fleet.cxf.service;

import com.fleet.cxf.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author April Han
 */
@WebService(serviceName = "userService", targetNamespace = "http://service.cxf.fleet.com")
public interface UserService {

    @WebMethod
    User get(@WebParam(name = "id") Long id);

    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    String getName(@WebParam(name = "id") Long id);
}
