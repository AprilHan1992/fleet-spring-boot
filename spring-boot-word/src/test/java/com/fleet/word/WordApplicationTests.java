package com.fleet.word;

import com.fleet.word.util.WordUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

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
}
