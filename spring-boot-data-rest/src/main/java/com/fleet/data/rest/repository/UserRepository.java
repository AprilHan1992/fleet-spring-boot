package com.fleet.data.rest.repository;

import com.fleet.data.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}
