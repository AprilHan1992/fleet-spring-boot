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
public class Book implements Serializable {

    private static final long serialVersionUID = -2103058138;

    private UInteger id;
    private String   title;
    private Integer  authorId;

    public Book() {}

    public Book(Book value) {
        this.id = value.id;
        this.title = value.title;
        this.authorId = value.authorId;
    }

    public Book(
        UInteger id,
        String   title,
        Integer  authorId
    ) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
    }

    public UInteger getId() {
        return this.id;
    }

    public void setId(UInteger id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Book (");

        sb.append(id);
        sb.append(", ").append(title);
        sb.append(", ").append(authorId);

        sb.append(")");
        return sb.toString();
    }
}
