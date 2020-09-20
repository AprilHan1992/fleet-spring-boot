package com.fleet.hutool;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTests {

    @Test
    public void simpleMail() {
        MailAccount ma = new MailAccount();
        ma.setHost("smtp.sina.com");
        ma.setPort(25);
        ma.setFrom("fleetsoft@sina.com");
        ma.setUser("fleetsoft@sina.com");
        ma.setPass("9a052dc39292b0ab");
        ma.setAuth(true);
        MailUtil.send(ma, "aprilhan1992@foxmail.com", "测试", "这是测试", false);
    }

    @Test
    public void htmlMail() {
        MailAccount ma = new MailAccount();
        ma.setHost("smtp.sina.com");
        ma.setPort(25);
        ma.setFrom("fleetsoft@sina.com");
        ma.setUser("fleetsoft@sina.com");
        ma.setPass("9a052dc39292b0ab");
        ma.setAuth(true);
        MailUtil.send(ma, "aprilhan1992@foxmail.com", "测试", "<P style=\"color:red\">这是测试</P>", true);
    }

    @Test
    public void attachmentMail() throws Exception {
        MailAccount ma = new MailAccount();
        ma.setHost("smtp.sina.com");
        ma.setPort(25);
        ma.setFrom("fleetsoft@sina.com");
        ma.setUser("fleetsoft@sina.com");
        ma.setPass("9a052dc39292b0ab");
        ma.setAuth(true);

        File file = new File("D:\\test.xls");
        File[] files = {file};
        MailUtil.send(ma, "aprilhan1992@foxmail.com", "测试", "<P style=\"color:red\">这是测试</P>", true, files);
    }
}
