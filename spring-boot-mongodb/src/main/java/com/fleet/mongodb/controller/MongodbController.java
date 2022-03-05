package com.fleet.mongodb.controller;

import com.fleet.mongodb.entity.User;
import com.fleet.mongodb.repository.UserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mongodb")
public class MongodbController {

    @Resource
    MongoTemplate mongoTemplate;

    @Resource
    UserRepository userRepository;

    @RequestMapping("/insert")
    User insert() {
        User user = new User();
        user.setName("张三");
        return mongoTemplate.insert(user);
    }

    @RequestMapping("/get")
    User get() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("测试"));
        return mongoTemplate.findOne(query, User.class);
    }

    @RequestMapping("/get1")
    User get1() {
        return userRepository.findByName("测试");
    }
}
