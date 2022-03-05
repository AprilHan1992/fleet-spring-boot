package com.fleet.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.fleet.graphql.entity.Author;
import com.fleet.graphql.entity.AuthorInput;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
public class AuthorMutationResolver implements GraphQLMutationResolver {

    public Author insertAuthor(AuthorInput authorInput) {
        Author author = new Author();
        author.setId(3);
        author.setName(authorInput.getName());
        author.setAge(authorInput.getAge());
        return author;
    }
}
