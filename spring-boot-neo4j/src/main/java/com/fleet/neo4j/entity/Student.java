package com.fleet.neo4j.entity;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * 学生节点
 */
@NodeEntity
public class Student {

    /**
     * 主键，自定义主键策略，使用UUID生成
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生选的所有课程
     */
    @Relationship(type = "课程")
    private List<Lesson> lessons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, List<Lesson> lessons) {
        this.name = name;
        this.lessons = lessons;
    }
}
