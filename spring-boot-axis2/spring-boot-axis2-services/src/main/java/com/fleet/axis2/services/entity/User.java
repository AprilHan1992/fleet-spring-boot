package com.fleet.axis2.services.entity;

import java.io.Serializable;

/**
 * @author April Han
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

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
