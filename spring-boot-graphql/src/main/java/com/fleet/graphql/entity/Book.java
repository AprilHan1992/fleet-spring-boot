package com.fleet.graphql.entity;


import java.io.Serializable;

/**
 * 书籍信息
 *
 * @author April Han
 */
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    public Book() {
    }

    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者信息
     */
    private Author author;

    /**
     * 出版商
     */
    private String publisher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
