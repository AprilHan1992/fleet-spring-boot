package com.fleet.postgis.controller;

import com.fleet.postgis.entity.Gis;
import com.fleet.postgis.service.GisService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/gis")
public class GisController {

    @Resource
    GisService gisService;

    @RequestMapping("/insert")
    public void insert(@RequestBody Gis gis) {
        gisService.insert(gis);
    }

    @RequestMapping("/get")
    public Gis get(Long id) {
        return gisService.get(id);
    }

    @RequestMapping("/list")
    public List<Gis> list() {
        return gisService.list();
    }

    @RequestMapping("/circle")
    public List<Gis> circle(@RequestParam("longitude") Double longitude, @RequestParam("latitude") Double latitude, @RequestParam("radius") Double radius) {
        return gisService.circle(longitude, latitude, radius);
    }
}
