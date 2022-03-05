
package com.fleet.ws.client.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UserService", targetNamespace = "http://services.ws.fleet.com")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserService {


    /**
     * 
     * @param arg0
     * @return
     *     returns com.fleet.ws.client.service.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "get", targetNamespace = "http://services.ws.fleet.com", className = "com.fleet.ws.client.service.Get")
    @ResponseWrapper(localName = "getResponse", targetNamespace = "http://services.ws.fleet.com", className = "com.fleet.ws.client.service.GetResponse")
    @Action(input = "http://services.ws.fleet.com/UserService/getRequest", output = "http://services.ws.fleet.com/UserService/getResponse")
    public User get(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getName", targetNamespace = "http://services.ws.fleet.com", className = "com.fleet.ws.client.service.GetName")
    @ResponseWrapper(localName = "getNameResponse", targetNamespace = "http://services.ws.fleet.com", className = "com.fleet.ws.client.service.GetNameResponse")
    @Action(input = "http://services.ws.fleet.com/UserService/getNameRequest", output = "http://services.ws.fleet.com/UserService/getNameResponse")
    public String getName(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

}