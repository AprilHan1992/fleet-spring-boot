package com.fleet.gemfire.controller;

import com.fleet.gemfire.entity.User;
import com.fleet.gemfire.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @RequestMapping("/insert")
    public void insert(@RequestBody User user) {
        userRepository.save(user);
    }

    @RequestMapping("/findByName/{name}")
    public User findByName(@PathVariable("name") String name) {
        return userRepository.findByName(name);
    }

    @RequestMapping("/findByAgeGreaterThan")
    public Iterable<User> findByAgeGreaterThan(Integer greaterThanAge) {
        return userRepository.findByAgeGreaterThan(greaterThanAge);
    }

    @RequestMapping("/findByAgeLessThan")
    public Iterable<User> findByAgeLessThan(Integer lessThanAge) {
        return userRepository.findByAgeLessThan(lessThanAge);
    }

    @RequestMapping("/findByAgeGreaterThanAndAgeLessThan")
    public Iterable<User> findByAgeGreaterThanAndAgeLessThan(@RequestParam("greaterThanAge") Integer greaterThanAge, @RequestParam("lessThanAge") Integer lessThanAge) {
        return userRepository.findByAgeGreaterThanAndAgeLessThan(greaterThanAge, lessThanAge);
    }

    @RequestMapping("/list")
    public Iterable<User> list() {
        return userRepository.findAll();
    }
}
