package com.fleet.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.fleet.elasticsearch.entity.User;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, Long> {
}
