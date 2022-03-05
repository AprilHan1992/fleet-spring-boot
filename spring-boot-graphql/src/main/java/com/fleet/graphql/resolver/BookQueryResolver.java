package com.fleet.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fleet.graphql.entity.Author;
import com.fleet.graphql.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author April Han
 */
@Component
public class BookQueryResolver implements GraphQLQueryResolver {

    public Book getBookById(Integer id) {
        Author author = new Author();
        author.setId(1);
        author.setName("Craig Walls");
        author.setAge(40);
        Book book = new Book();
        book.setId(id);
        book.setTitle("Spring Boot 实战");
        book.setAuthor(author);
        book.setPublisher("人民邮电出版社");
        return book;
    }

    public List<Book> getBookList() {
        List<Book> list = new ArrayList<>();
        Author author1 = new Author();
        author1.setId(1);
        author1.setName("Craig Walls");
        author1.setAge(40);
        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Spring Boot 实战");
        book1.setAuthor(author1);
        book1.setPublisher("人民邮电出版社");
        list.add(book1);

        Author author2 = new Author();
        author2.setId(2);
        author2.setName("Bruce Eckel");
        author2.setAge(40);
        Book book2 = new Book();
        book2.setId(2);
        book2.setTitle("Java编程思想");
        book2.setAuthor(author2);
        book2.setPublisher("机械工业出版社");
        list.add(book2);
        return list;
    }
}
