package com.fleet.gemfire.repository;

import com.fleet.gemfire.entity.User;
import org.springframework.data.gemfire.repository.query.annotation.Trace;
import org.springframework.data.repository.CrudRepository;

/**
 * @author April Han
 */
public interface UserRepository extends CrudRepository<User, Long> {

    @Trace
    User findByName(String name);

    @Trace
    Iterable<User> findByAgeGreaterThan(Integer greaterThanAge);

    @Trace
    Iterable<User> findByAgeLessThan(Integer lessThanAge);

    @Trace
    Iterable<User> findByAgeGreaterThanAndAgeLessThan(Integer greaterThanAge, Integer lessThanAge);
}
