package com.fleet.sharding.jdbc.sharding.repository;

import com.fleet.sharding.jdbc.sharding.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
}
