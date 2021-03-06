
package com.fleet.ws.client.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "UserServiceImpl", targetNamespace = "http://services.ws.fleet.com", wsdlLocation = "http://127.0.0.1:8002/services/userService?wsdl")
public class UserServiceImpl
    extends Service
{

    private final static URL USERSERVICEIMPL_WSDL_LOCATION;
    private final static WebServiceException USERSERVICEIMPL_EXCEPTION;
    private final static QName USERSERVICEIMPL_QNAME = new QName("http://services.ws.fleet.com", "UserServiceImpl");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.0.1:8002/services/userService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        USERSERVICEIMPL_WSDL_LOCATION = url;
        USERSERVICEIMPL_EXCEPTION = e;
    }

    public UserServiceImpl() {
        super(__getWsdlLocation(), USERSERVICEIMPL_QNAME);
    }

    public UserServiceImpl(WebServiceFeature... features) {
        super(__getWsdlLocation(), USERSERVICEIMPL_QNAME, features);
    }

    public UserServiceImpl(URL wsdlLocation) {
        super(wsdlLocation, USERSERVICEIMPL_QNAME);
    }

    public UserServiceImpl(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, USERSERVICEIMPL_QNAME, features);
    }

    public UserServiceImpl(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserServiceImpl(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns UserService
     */
    @WebEndpoint(name = "UserServicePort")
    public UserService getUserServicePort() {
        return super.getPort(new QName("http://services.ws.fleet.com", "UserServicePort"), UserService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UserService
     */
    @WebEndpoint(name = "UserServicePort")
    public UserService getUserServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.ws.fleet.com", "UserServicePort"), UserService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (USERSERVICEIMPL_EXCEPTION!= null) {
            throw USERSERVICEIMPL_EXCEPTION;
        }
        return USERSERVICEIMPL_WSDL_LOCATION;
    }

}
