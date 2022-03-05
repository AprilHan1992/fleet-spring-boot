package com.fleet.neo4j.repository;


import com.fleet.neo4j.entity.Lesson;
import org.springframework.data.repository.CrudRepository;

/**
 * 课程节点 Repository
 */
public interface LessonRepository extends CrudRepository<Lesson, Long> {

    Lesson findByName(String name);
}
