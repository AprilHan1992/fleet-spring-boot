package com.fleet.graphql.entity;


/**
 * 书籍信息
 *
 * @author April Han
 */
public class BookInput extends Book {

    private static final long serialVersionUID = 1L;

    public BookInput() {
    }

    /**
     * 作者信息
     */
    private AuthorInput authorInput;

    public AuthorInput getAuthorInput() {
        return authorInput;
    }

    public void setAuthorInput(AuthorInput authorInput) {
        this.authorInput = authorInput;
    }
}
