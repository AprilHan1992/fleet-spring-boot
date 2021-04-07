package com.fleet.cxf.service;

import com.fleet.cxf.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author April Han
 */
@WebService(name = "UserService", targetNamespace = "http://services.cxf.fleet.com")
public interface UserService {

    @WebMethod
    User get(@WebParam(name = "id") Long id);

    @WebMethod
    String getName(@WebParam(name = "id") Long id);
}
