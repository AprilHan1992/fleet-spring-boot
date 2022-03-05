package com.fleet.word;

import com.alibaba.fastjson.JSON;
import com.fleet.word.util.WordUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordApplicationTests {

    @Test
    public void read() {
        WordUtil wordUtil = new WordUtil();
        File file = new File("D:\\test.docx");
        String text = wordUtil.read(file);
        System.out.println(text);
    }

    @Test
    public void readExcelInWord() {
        WordUtil wordUtil = new WordUtil();
        File file = new File("D:\\test.docx");
//        File file = new File("D:\\test.doc");
        Map<Integer, Map<Integer, List<String>>> map = wordUtil.readExcelInWord(file);
        System.out.println(JSON.toJSONString(map));
    }
}
