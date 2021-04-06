package com.fleet.ws.client.config;

import com.fleet.ws.client.entity.UserRequest;
import com.fleet.ws.client.entity.UserResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class WsClient extends WebServiceGatewaySupport {

    public UserResponse get(Long id) {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(id);
        return (UserResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://127.0.0.1:8002/services/userService?wsdl", userRequest);
    }
}
