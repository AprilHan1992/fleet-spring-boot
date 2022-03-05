package com.fleet.webflux.service;

import com.fleet.webflux.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

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
