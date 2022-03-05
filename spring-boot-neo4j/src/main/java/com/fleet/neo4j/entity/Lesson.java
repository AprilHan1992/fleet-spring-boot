package com.fleet.neo4j.entity;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * 课程节点
 */
@NodeEntity
public class Lesson {

    /**
     * 主键，自定义主键策略，使用UUID生成
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 课程名称
     */
    private String name;

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

    public Lesson() {
    }

    public Lesson(String name) {
        this.name = name;
    }
}
