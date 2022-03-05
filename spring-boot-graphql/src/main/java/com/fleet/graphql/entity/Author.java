package com.fleet.graphql.entity;

import java.io.Serializable;

/**
 * 作者信息
 *
 * @author April Han
 */
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    public Author() {
    }

    /**
     * id
     */
    private Integer id;

    /**
     * 作者名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
