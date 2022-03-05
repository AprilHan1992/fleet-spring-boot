package com.fleet.excel.controller;

import com.fleet.excel.entity.User;
import com.fleet.excel.util.ExcelUtil;
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
        ExcelUtil<User> excelUtil = new ExcelUtil<>(User.class);
        return excelUtil.read(file);
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        ExcelUtil<User> excelUtil = new ExcelUtil<>(User.class);

        response.setHeader("Content-disposition", "attachment;filename=" + new String("测试.xls".getBytes(), StandardCharsets.ISO_8859_1));
        response.setContentType("application/ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();

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
        excelUtil.export("用户", list, os);
    }

    @RequestMapping("/temp")
    public void temp(HttpServletResponse response) throws Exception {
        ExcelUtil<User> excelUtil = new ExcelUtil<>(User.class);

        response.setHeader("Content-disposition", "attachment;filename=" + new String("用户.xls".getBytes(), StandardCharsets.ISO_8859_1));
        response.setContentType("application/ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();

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
        excelUtil.temp(list, os);
    }
}
