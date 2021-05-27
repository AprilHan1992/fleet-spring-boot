package com.fleet.reactor.service;

import com.fleet.reactor.entity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import reactor.core.Reactor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.event.Event;
import reactor.function.Consumer;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static reactor.event.selector.Selectors.$;

/**
 * @author April Han
 */
@Service
public class UserService implements InitializingBean {

    @Resource
    private Reactor reactor;

    @Override
    public void afterPropertiesSet() throws Exception {
        reactor.on($("hiHandler"), new Consumer<Event<User>>() {
            @Override
            public void accept(Event<User> event) {
                System.out.println(
                        "Received User Object With "
                                + "id:" + event.getData().getId()
                                + ", name:" + event.getData().getName()
                );
            }
        });
    }

    private Map<Long, User> data = new ConcurrentHashMap<>();

    public Mono<User> insert(User user) {
        data.put(user.getId(), user);
        return Mono.just(user);
    }

    public Mono<User> delete(Long id) {
        return Mono.justOrEmpty(data.remove(id));
    }

    public Mono<User> update(User user) {
        data.put(user.getId(), user);
        return Mono.just(user);
    }

    public Mono<User> get(Long id) {
        return Mono.justOrEmpty(data.get(id));
    }

    public Flux<User> list() {
        return Flux.fromIterable(data.values());
    }

    public Flux<User> list(Flux<Long> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(data.get(id)));
    }

    public Flux<User> list(List<Long> ids) {
        return Flux.fromIterable(ids).flatMap(id -> Mono.justOrEmpty(data.get(id)));
    }
}
