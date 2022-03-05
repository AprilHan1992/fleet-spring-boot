package com.fleet.hateoas.repository;

import com.fleet.hateoas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUid(Long uid);

    User findByName(String name);
}
