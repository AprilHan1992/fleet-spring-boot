package com.fleet.neo4j.controller;//package com.fleet.neo4j.controller;

import com.fleet.neo4j.entity.Lesson;
import com.fleet.neo4j.entity.Student;
import com.fleet.neo4j.repository.LessonRepository;
import com.fleet.neo4j.repository.StudentRepository;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class Neo4jController {

    @Resource
    private SessionFactory sessionFactory;

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private LessonRepository lessonRepository;

    @RequestMapping("/init")
    public void init() {
        // 初始化课程
        Lesson math = new Lesson("数学");
        Lesson chinese = new Lesson("语文");
        Lesson english = new Lesson("英语");
        Lesson physics = new Lesson("物理");
        Lesson chemistry = new Lesson("化学");
        Lesson biology = new Lesson("生物");
        Lesson geography = new Lesson("地理");
        lessonRepository.save(math);
        lessonRepository.save(chinese);
        lessonRepository.save(english);
        lessonRepository.save(physics);
        lessonRepository.save(chemistry);
        lessonRepository.save(biology);
        lessonRepository.save(geography);

        // 学生关联课程
        List<Student> studentList = Arrays.asList(new Student("张三", Arrays.asList(math, chinese, english, physics)), new Student("李四", Arrays.asList(chemistry, biology, chinese)), new Student("Tom", Arrays.asList(math, geography, chinese)));
        studentRepository.saveAll(studentList);
    }

    @RequestMapping("/delete")
    public void delete() {
        // 使用语句删除
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.query("match (n)-[r]-() delete n,r", new HashMap<>());
        session.query("match (n)-[r]-() delete r", new HashMap<>());
        session.query("match (n) delete n", new HashMap<>());
        transaction.commit();

        // 使用 repository 删除
        studentRepository.deleteAll();
        lessonRepository.deleteAll();
    }

    @RequestMapping("/lessonsOfStudent")
    public List<Lesson> lessonsOfStudent(String name, int depth) {
        List<Lesson> lessons = new ArrayList<>();
        studentRepository.findByName(name, depth).ifPresent(student -> lessons.addAll(student.getLessons()));
        return lessons;
    }
}
