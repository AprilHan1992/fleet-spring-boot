package com.fleet.test.entity;

import java.io.Serializable;

/**
 * @author April Han
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
