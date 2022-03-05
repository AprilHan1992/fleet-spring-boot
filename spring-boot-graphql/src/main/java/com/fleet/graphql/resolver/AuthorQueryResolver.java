package com.fleet.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fleet.graphql.entity.Author;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author April Han
 */
@Component
public class AuthorQueryResolver implements GraphQLQueryResolver {

    public Author getAuthorById(Integer id) {
        Author author = new Author();
        author.setId(id);
        author.setName("Craig Walls");
        author.setAge(40);
        return author;
    }

    public List<Author> list() {
        List<Author> list = new ArrayList<>();
        Author author1 = new Author();
        author1.setId(1);
        author1.setName("Craig Walls");
        author1.setAge(40);
        list.add(author1);

        Author author2 = new Author();
        author2.setId(2);
        author2.setName("Bruce Eckel");
        author2.setAge(36);
        list.add(author2);
        return list;
    }
}
