package com.fleet.axis2.services;

import com.alibaba.fastjson.JSON;
import com.fleet.axis2.services.entity.User;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.namespace.QName;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Axis2ServicesApplicationTests {

    @Test
    public void get() throws Exception {
        RPCServiceClient rpcServiceClient = new RPCServiceClient();
        Options options = rpcServiceClient.getOptions();
        EndpointReference endpointReference = new EndpointReference("http://localhost:8001/services/userService");
        options.setTo(endpointReference);
        QName opName = new QName("http://services.axis2.fleet.com", "get");
        Object[] args = new Object[]{1};
        Class[] returnTypes = new Class[]{User.class};
        Object[] objects = rpcServiceClient.invokeBlocking(opName, args, returnTypes);
        System.out.println("查询到用户:" + JSON.toJSONString(objects[0]));
    }

    @Test
    public void getName() throws Exception {
        RPCServiceClient rpcServiceClient = new RPCServiceClient();
        Options options = rpcServiceClient.getOptions();
        EndpointReference endpointReference = new EndpointReference("http://localhost:8001/services/userService");
        options.setTo(endpointReference);
        QName opName = new QName("http://services.axis2.fleet.com", "getName");
        Object[] args = new Object[]{1};
        Class[] returnTypes = new Class[]{String.class};
        Object[] objects = rpcServiceClient.invokeBlocking(opName, args, returnTypes);
        System.out.println("查询到用户名:" + objects[0]);
    }
}
