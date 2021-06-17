package com.fleet.qywx;

import com.fleet.qywx.service.MediaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MediaServiceTests {

    @Resource
    MediaService mediaService;

    @Test
    public void upload() {
        System.out.println(mediaService.upload("file", new File("D://mytext.txt")));
    }
}
