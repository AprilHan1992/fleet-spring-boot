package com.fleet.map;

import com.fleet.map.service.TencentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TencentServiceTests {

    @Resource
    TencentService tencentService;

    @Test
    public void regeo() throws IOException {
        Double longitude = 116.39747;
        Double latitude = 39.90882;
        System.out.println(tencentService.regeo(longitude, latitude));
    }

    @Test
    public void geo() throws IOException {
        System.out.println(tencentService.geo("北京市东城区东华门街道天安门"));
    }
}
