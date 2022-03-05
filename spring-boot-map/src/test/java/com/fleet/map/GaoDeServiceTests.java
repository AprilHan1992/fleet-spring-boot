package com.fleet.map;

import com.fleet.map.service.GaoDeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GaoDeServiceTests {

    @Resource
    GaoDeService gaoDeService;

    @Test
    public void regeo() throws IOException {
        Double longitude = 116.397477;
        Double latitude = 39.908692;
        System.out.println(gaoDeService.regeo(longitude, latitude));
    }

    @Test
    public void geo() throws IOException {
        System.out.println(gaoDeService.geo("北京市东城区东华门街道天安门"));
    }
}
