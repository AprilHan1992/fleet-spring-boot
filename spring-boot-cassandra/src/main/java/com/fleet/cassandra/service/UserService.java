package com.fleet.cassandra.service;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.fleet.cassandra.entity.User;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserService {

    @Resource
    private CassandraTemplate cassandraTemplate;

    public User save(User user) {
        return cassandraTemplate.insert(user);
    }

    public User selectOneById(Long id) {
        return cassandraTemplate.selectOneById(id, User.class);
    }

    public Iterable<User> select() {
        return cassandraTemplate.select("SELECT * FROM user;", User.class);
    }

    public Iterable<User> select(User user) {
        Select select = QueryBuilder.select().from("user").allowFiltering();
        select.where(QueryBuilder.like("name", user.getName()));
        return cassandraTemplate.select(select, User.class);
    }
}
