package com.fleet.ws.client.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author April Han
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {"id", "name"})
public class User {

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
