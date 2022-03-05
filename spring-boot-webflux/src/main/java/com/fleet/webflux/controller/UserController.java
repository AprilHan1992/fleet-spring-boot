package com.fleet.webflux.controller;

import com.fleet.webflux.entity.User;
import com.fleet.webflux.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("insert")
    public Mono<User> insert(@RequestBody User user) {
        printlnThread("调用insert");
        return userService.insert(user);
    }

    @RequestMapping("/delete/{id}")
    public Mono<User> delete(@PathVariable("id") Long id) {
        printlnThread("调用delete");
        return userService.delete(id);
    }

    @RequestMapping("update")
    public Mono<User> update(@RequestBody User user) {
        printlnThread("调用update");
        return userService.update(user);
    }

    @RequestMapping("/get/{id}")
    public Mono<User> get(@PathVariable("id") Long id) {
        printlnThread("调用get");
        return userService.get(id);
    }

    @RequestMapping("list")
    public Flux<User> list() {
        printlnThread("调用list");
        return userService.list();
    }

    @RequestMapping("/listByIds")
    public Flux<User> listByIds(@RequestParam("ids") List<Long> ids) {
        printlnThread("调用listByIds");
        return userService.list(ids);
    }

    /**
     * 打印当前线程
     */
    private void printlnThread(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("UserController[" + threadName + "]: " + object);
    }
}
