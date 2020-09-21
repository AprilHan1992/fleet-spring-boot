package com.fleet.mail;

import com.fleet.mail.util.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTests {

    @Resource
    MailUtil mailUtil;

    @Test
    public void simpleMail() throws Exception {
        mailUtil.simpleMail("fleetsoft@sina.com", "aprilhan1992@foxmail.com", "测试", "这是测试");
    }

    @Test
    public void textMail() throws Exception {
        mailUtil.textMail("fleetsoft@sina.com", "aprilhan1992@foxmail.com", "测试", "这是测试");
    }

    @Test
    public void htmlMail() throws Exception {
        mailUtil.htmlMail("fleetsoft@sina.com", "aprilhan1992@foxmail.com", "测试", "<P style=\"color:red\">这是测试</P>");
    }

    @Test
    public void attachmentMail() throws Exception {
        Map<String, File> files = new HashMap<>();
        File file = new File("D:\\test.xls");
        files.put("test.xls", file);
        mailUtil.attachmentMail("fleetsoft@sina.com", "aprilhan1992@foxmail.com", "测试", "<P style=\"color:red\">这是测试</P>", files);
    }

    @Test
    public void inlineMail() throws Exception {
        Map<String, File> files = new HashMap<>();
        File file = new File("D:\\avatar.jpg");
        files.put("file", file);
        mailUtil.inlineMail("fleetsoft@sina.com", "aprilhan1992@foxmail.com", "测试", "<body><p style='color:red;'>Hello Html Email</p><img src='cid:file'/></body>", files);
    }

    @Test
    public void templateMail() throws Exception {
        Map<String, Object> variables = new HashMap<>();
        variables.put("username", "fleet");
        mailUtil.templateMail("fleetsoft@sina.com", "aprilhan1992@foxmail.com", "测试", "hello", variables);
    }
}
