package com.fleet.hateoas.controller;

import com.fleet.hateoas.entity.User;
import com.fleet.hateoas.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @GetMapping("/findByUid/{uid}")
    public User findByUid(@PathVariable("uid") Long uid) throws NotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUid(uid));
        if (!user.isPresent()) {
            throw new NotFoundException("查询不到用户信息! uid:" + uid);
        }
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).findByUid(uid));
        user.get().add(linkBuilder.withRel("findByUid"));
        return user.get();
    }

    @GetMapping("/findByName/{name}")
    public User findByName(@PathVariable("name") String name) throws NotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByName(name));
        if (!user.isPresent()) {
            throw new NotFoundException("查询不到用户信息! name:" + name);
        }
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).findByName(name));
        user.get().add(linkBuilder.withRel("findByName"));
        return user.get();
    }
}
