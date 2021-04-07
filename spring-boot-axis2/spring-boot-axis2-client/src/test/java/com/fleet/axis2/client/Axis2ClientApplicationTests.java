package com.fleet.axis2.client;

import com.alibaba.fastjson.JSON;
import com.fleet.axis2.client.service.UserServiceCallbackHandler;
import com.fleet.axis2.client.service.UserServiceStub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Axis2ClientApplicationTests {

    @Test
    public void get() throws Exception {
        UserServiceStub.Get get = new UserServiceStub.Get();
        get.setId(1L);
        UserServiceStub userServiceStub = new UserServiceStub();
        GetResponseHandler getResponseHandler = new GetResponseHandler();
        UserServiceStub.GetResponse getResponse = userServiceStub.get(get);
        getResponseHandler.receiveResultGet(getResponse);
        userServiceStub.startget(get, getResponseHandler);
        UserServiceStub.User user = getResponse.get_return();
        System.out.println("查询到用户:" + JSON.toJSONString(user));
    }

    static class GetResponseHandler extends UserServiceCallbackHandler {
        public void receiveResultGet(UserServiceStub.GetResponse result) {
            super.receiveResultget(result);
        }
    }

    @Test
    public void getName() throws Exception {
        UserServiceStub.GetName getName = new UserServiceStub.GetName();
        getName.setId(1L);
        UserServiceStub userServiceStub = new UserServiceStub();
        GetNameResponseHandler getNameResponseHandler = new GetNameResponseHandler();
        UserServiceStub.GetNameResponse getNameResponse = userServiceStub.getName(getName);
        getNameResponseHandler.receiveResultgetName(getNameResponse);
        userServiceStub.startgetName(getName, getNameResponseHandler);
        String name = getNameResponse.get_return();
        System.out.println("查询到用户名:" + name);
    }

    static class GetNameResponseHandler extends UserServiceCallbackHandler {
        public void receiveResultgetName(UserServiceStub.GetNameResponse result) {
            super.receiveResultgetName(result);
        }
    }
}
