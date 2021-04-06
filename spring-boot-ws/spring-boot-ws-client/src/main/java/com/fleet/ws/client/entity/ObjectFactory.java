package com.fleet.ws.client.entity;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public UserRequest createUserRequest() {
        return new UserRequest();
    }

    public UserResponse createUserResponse() {
        return new UserResponse();
    }

    public User createUser() {
        return new User();
    }
}
