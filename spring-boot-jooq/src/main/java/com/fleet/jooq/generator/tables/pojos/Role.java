/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;

import org.jooq.types.UInteger;


/**
 * 角色信息
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Role implements Serializable {

    private static final long serialVersionUID = 1227268721;

    private UInteger id;
    private String   name;
    private Integer  sort;
    private UInteger upperId;

    public Role() {}

    public Role(Role value) {
        this.id = value.id;
        this.name = value.name;
        this.sort = value.sort;
        this.upperId = value.upperId;
    }

    public Role(
        UInteger id,
        String   name,
        Integer  sort,
        UInteger upperId
    ) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.upperId = upperId;
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

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public UInteger getUpperId() {
        return this.upperId;
    }

    public void setUpperId(UInteger upperId) {
        this.upperId = upperId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Role (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(sort);
        sb.append(", ").append(upperId);

        sb.append(")");
        return sb.toString();
    }
}