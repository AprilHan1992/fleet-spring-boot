package com.fleet.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.fleet.easyexcel.entity.User;
import com.fleet.easyexcel.listener.UserListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyExcelApplicationTests {

    @Test
    public void read() {
        String fileName = "D:\\test.xlsx";
        UserListener userListener = new UserListener();
        EasyExcel.read(fileName, User.class, userListener).sheet().doRead();
        System.out.println(JSON.toJSONString(userListener.getList()));
        System.out.println("----执行完毕----");
    }

    @Test
    public void export() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            User user = new User();
            user.setId((long) i);
            user.setName("张三");
            user.setSex(i % 2);
            user.setAvatar("D:\\avatar.jpg");
            user.setBirth(new Date("1992/10/23"));
            user.setIdNo("20201918");
            user.setScore(60.0203);
            list.add(user);
        }
        String fileName = "D:\\test.xlsx";
        EasyExcel.write(fileName, User.class).sheet("用户").doWrite(list);
        System.out.println("----执行完毕----");
    }
}
