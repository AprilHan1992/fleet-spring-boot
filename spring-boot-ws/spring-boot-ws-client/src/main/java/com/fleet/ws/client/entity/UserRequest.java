package com.fleet.ws.client.entity;

import javax.xml.bind.annotation.*;

/**
 * @author April Han
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"id", "name"})
@XmlRootElement(name = "userRequest")
public class UserRequest {

    @XmlElement(required = true)
    private Long id;

    @XmlElement(required = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
