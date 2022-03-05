package com.fleet.sharding.jdbc.proxy.repository;

import com.fleet.sharding.jdbc.proxy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
