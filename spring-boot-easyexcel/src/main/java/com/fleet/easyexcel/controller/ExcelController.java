package com.fleet.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.fleet.easyexcel.entity.User;
import com.fleet.easyexcel.listener.UserListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @RequestMapping("/read")
    public List<User> read(@RequestParam(value = "file") MultipartFile file) throws Exception {
        if (file == null) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return null;
        }
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!"xls".equals(suffix) && !"xlsx".equals(suffix)) {
            return null;
        }
        UserListener userListener = new UserListener();
        EasyExcel.read(file.getInputStream(), User.class, userListener).sheet().doRead();
        return userListener.getList();
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户.xlsx".getBytes(), StandardCharsets.ISO_8859_1));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        OutputStream os = response.getOutputStream();

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
        EasyExcel.write(os, User.class).sheet("用户").doWrite(list);
    }
}
