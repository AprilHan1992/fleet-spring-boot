package com.fleet.postgis;

import com.alibaba.fastjson.JSON;
import com.fleet.postgis.entity.Gis;
import com.fleet.postgis.service.GisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostGisApplicationTests {

    @Resource
    GisService gisService;

    @Test
    public void insert() {
        gisService.insert(new Gis("北京天安门", 116.397, 39.908));
        gisService.insert(new Gis("国家大剧院", 116.389, 39.904));
    }

    @Test
    public void polygon() {
        List<Gis> gisList = new ArrayList<>();
        gisList.add(new Gis(116.200, 39.800));
        gisList.add(new Gis(116.397, 40.200));
        gisList.add(new Gis(116.500, 39.800));
        List<Gis> list = gisService.polygon(gisList);
        System.out.println(JSON.toJSONString(list));
    }
}
