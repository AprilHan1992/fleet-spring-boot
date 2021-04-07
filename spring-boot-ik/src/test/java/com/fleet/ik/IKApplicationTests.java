package com.fleet.ik;

import com.fleet.ik.util.IKUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IKApplicationTests {

    @Test
    public void hello() throws Exception {
        String text = "中国太平洋成立九十周年了！";
        List<String> list = IKUtil.participle(text);
        System.out.println(list);
    }
}
