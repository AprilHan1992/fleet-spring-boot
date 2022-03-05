package com.fleet.mail.controller;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Resource
    MailSender mailSender;

    @RequestMapping("/send")
    public void send() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fleetsoft@sina.com");
        message.setSubject("Hello");
        message.setText("Hello from Spring Boot Application");
        message.setTo("aprilhan1992@foxmail.com");
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
