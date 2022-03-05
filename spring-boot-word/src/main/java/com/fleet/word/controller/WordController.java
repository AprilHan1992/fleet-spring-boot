package com.fleet.word.controller;

import com.fleet.word.util.WordUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/word")
public class WordController {

    @RequestMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        List<Map<String, Object>> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1023);
            map.put("name", "张三");
            map.put("sex", "男");
            userList.add(map);
        }

        Map<String, Object> review = new HashMap<>();
        review.put("userList", userList);

        response.setHeader("Content-disposition", "attachment;filename=" + new String("用户表.doc".getBytes(), StandardCharsets.ISO_8859_1));
        response.setContentType("application/ms-word;charset=utf-8");
        OutputStream os = response.getOutputStream();

        WordUtil wordUtil = new WordUtil();
        wordUtil.export(review, "D:\\", "User.xml", os);
    }
}
