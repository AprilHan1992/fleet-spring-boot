package com.fleet.mongodb.repository;

import com.fleet.mongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends MongoRepository<User, Long> {

    public User findByName(String name);

}
