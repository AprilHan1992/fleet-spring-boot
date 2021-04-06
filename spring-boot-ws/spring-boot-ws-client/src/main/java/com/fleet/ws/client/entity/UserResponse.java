package com.fleet.ws.client.entity;

import javax.xml.bind.annotation.*;

/**
 * @author April Han
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"user"})
@XmlRootElement(name = "userResponse")
public class UserResponse {

    @XmlElement(required = true)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
