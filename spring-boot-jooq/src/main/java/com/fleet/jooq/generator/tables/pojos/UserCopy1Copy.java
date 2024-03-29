/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;

import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserCopy1Copy implements Serializable {

    private static final long serialVersionUID = 1621742375;

    private UInteger id;
    private String   name;

    public UserCopy1Copy() {}

    public UserCopy1Copy(UserCopy1Copy value) {
        this.id = value.id;
        this.name = value.name;
    }

    public UserCopy1Copy(
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
        StringBuilder sb = new StringBuilder("UserCopy1Copy (");

        sb.append(id);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }
}
