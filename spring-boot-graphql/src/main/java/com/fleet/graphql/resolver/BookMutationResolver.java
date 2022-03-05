package com.fleet.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.fleet.graphql.entity.Author;
import com.fleet.graphql.entity.AuthorInput;
import com.fleet.graphql.entity.Book;
import com.fleet.graphql.entity.BookInput;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
public class BookMutationResolver implements GraphQLMutationResolver {

    public Book insertBook(BookInput bookInput) {
        Book book = new Book();
        book.setId(3);
        book.setTitle(bookInput.getTitle());
        book.setPublisher(bookInput.getPublisher());
        AuthorInput authorInput = bookInput.getAuthorInput();
        if (authorInput != null) {
            Author author = new Author();
            author.setId(3);
            author.setName(authorInput.getName());
            author.setAge(authorInput.getAge());
            book.setAuthor(author);
        }
        return book;
    }
}
