package com.fleet.map;

import com.fleet.map.service.BaiduService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaiduServiceTests {

    @Resource
    BaiduService baiduService;

    @Test
    public void regeo() throws IOException {
        Double longitude = 116.30842;
        Double latitude = 40.05703;
        System.out.println(baiduService.regeo(longitude, latitude));
    }

    @Test
    public void geo() throws IOException {
        System.out.println(baiduService.geo("北京市海淀区上地十街10号"));
    }
}
