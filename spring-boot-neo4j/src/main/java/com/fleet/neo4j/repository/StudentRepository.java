package com.fleet.neo4j.repository;

import com.fleet.neo4j.entity.Student;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * 学生节点 Repository
 */
public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findByName(String name);

    Optional<Student> findByName(String name, @Depth int depth);
}
