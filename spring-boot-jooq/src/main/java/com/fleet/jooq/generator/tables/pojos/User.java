/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;

import org.jooq.types.UInteger;


/**
 * 用户信息
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User implements Serializable {

    private static final long serialVersionUID = -2067210243;

    private UInteger id;
    private String   name;

    public User() {}

    public User(User value) {
        this.id = value.id;
        this.name = value.name;
    }

    public User(
        UInteger id,
        String   name
    ) {
        this.id = id;
        this.name = name;
    }

    public UInteger getId() {
        return this.id;
    }

    public void setId(UInteger id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User (");

        sb.append(id);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }
}
