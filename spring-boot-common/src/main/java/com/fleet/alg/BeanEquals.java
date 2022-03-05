package com.fleet.alg;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 利用 List 中 contains 方法去重
 *
 * @author April Han
 */
public class BeanEquals {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "fleet"));
        list.add(new User(1L, "fleet1"));
        list.add(new User(1L, "fleet"));
        list.add(new User(2L, "fleet1"));
        list.add(new User(2L, "fleet1"));

        List<User> rList = new ArrayList<>();
        // 使用 contains 判断，是否有相同的元素
        for (User user : list) {
            if (!rList.contains(user)) {
                rList.add(user);
            }
        }
        for (User user : rList) {
            System.out.println(JSON.toJSONString(user));
        }
    }

    public static class User {

        private Long id;

        private String name;

        public User(Long id, String name) {
            this.id = id;
            this.name = name;
        }

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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            User user = (User) obj;
            return Objects.equals(id, user.id) && Objects.equals(name, user.name);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
