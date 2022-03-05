package com.fleet.spock


import com.fleet.spock.service.UserService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Unroll

import javax.annotation.Resource

@ActiveProfiles("dev")
@SpringBootTest
class UserServiceTests extends Specification {


    /**
     * Spock 提供了如下基本构造块：
     *
     * where: 以表格的形式提供测试数据集合
     * when: 触发行为，比如调用指定方法或函数
     * then: 做出断言表达式
     * expect: 期望的行为，when-then 的精简版
     * given: mock 单测中指定 mock 数据
     * thrown: 如果在 when 方法中抛出了异常，则在这个子句中会捕获到异常并返回
     * def setup() {} ：每个测试运行前的启动方法
     * def cleanup() {} : 每个测试运行后的清理方法
     * def setupSpec() {} : 第一个测试运行前的启动方法
     * def cleanupSpec() {} : 最后一个测试运行后的清理方法
     */


    @Resource
    UserService userService;

    def setup() {}

    @Unroll
    def "testGet1"() {
        when:
        def r = userService.get(id)
        then:
        name == r.getName()

//        expect:
//        def r = userService.get(id)
//        name == r.getName()

        where:
        id | name
        1L | 'fleet-1'
        2L | "fleet-2"
    }

    @Unroll
    def "testGet2"() {
//        when:
//        def r = userService.get(id)
//        then:
//        name == r.getName()

        expect:
        def r = userService.get(id)
        name == r.getName()

        // where 反复调用
        where:
        id << [1L, 2L]
        name << ["fleet-1", "fleet-2"]
    }
}

