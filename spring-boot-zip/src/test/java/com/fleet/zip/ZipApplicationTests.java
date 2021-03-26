package com.fleet.zip;

import com.alibaba.fastjson.JSON;
import com.fleet.zip.util.ZipUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZipApplicationTests {

    @Test
    public void read() throws IOException {
        File zipFile = new File("D:\\test.zip");
        System.out.println(JSON.toJSONString(ZipUtil.read(zipFile)));
    }

    @Test
    public void zip() throws IOException {
        File zipFile = new File("D:\\test.zip");

        List<File> srcFileList = new ArrayList<>();
        File file = new File("D:\\test");
        srcFileList.add(file);
        ZipUtil.zip(zipFile, srcFileList);
    }

    @Test
    public void unzip() throws IOException {
        File file = new File("D:\\test.zip");
        ZipUtil.unzip(file, "D:\\test");
    }
}
