package com.fleet.sharding.jdbc.sharding.repository;

import com.fleet.sharding.jdbc.sharding.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
