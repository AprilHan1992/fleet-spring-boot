package com.fleet.excel;

import com.fleet.excel.entity.User;
import com.fleet.excel.util.ExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelApplicationTests {

    @Test
    public void read() throws Exception {
        File file = new File("D:\\test.xls");
        FileInputStream fis = new FileInputStream(file);
        ExcelUtil<User> excelUtil = new ExcelUtil<>(User.class);
        List<User> list = excelUtil.read(fis, "test信息");
        System.out.println("----执行完毕----");
    }

    @Test
    public void export() throws Exception {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            User user = new User();
            user.setId(1023L);
            user.setName("张三");
            user.setSex(1);
            user.setBirth(new Date("1992/10/23"));
            user.setIdNo("20201918");
            list.add(user);
        }
        FileOutputStream fos = new FileOutputStream("D:\\test.xls");
        ExcelUtil<User> excelUtil = new ExcelUtil<>(User.class);
        excelUtil.export(list, fos);
        System.out.println("----执行完毕----");
    }

    @Test
    public void temp() throws Exception {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            User user = new User();
            user.setId(1023L);
            user.setName("张三");
            user.setSex(1);
            user.setBirth(new Date("1992/10/23"));
            user.setIdNo("20201918");
            list.add(user);
        }
        FileOutputStream fos = new FileOutputStream("D:\\test.xls");
        ExcelUtil<User> excelUtil = new ExcelUtil<>(User.class);
        excelUtil.temp(list, fos);
        System.out.println("----执行完毕----");
    }

    @Test
    public void exportTemplate() throws Exception {
        FileOutputStream fos = new FileOutputStream("D:\\testTemplate.xls");
        ExcelUtil<User> excelUtil = new ExcelUtil<>(User.class);
        excelUtil.exportTemplate("test信息", fos);
        System.out.println("----执行完毕----");
    }
}
