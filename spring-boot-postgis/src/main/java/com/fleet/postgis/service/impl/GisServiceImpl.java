package com.fleet.postgis.service.impl;

import com.fleet.postgis.dao.GisDao;
import com.fleet.postgis.entity.Gis;
import com.fleet.postgis.service.GisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author April Han
 */
@Service
public class GisServiceImpl implements GisService {

    @Resource
    private GisDao gisDao;

    @Override
    public int insert(Gis gis) {
        gis.setGeometry(geometry(gis.getLongitude(), gis.getLatitude()));
        return gisDao.insert(gis);
    }

    @Override
    public Gis get(Long id) {
        return gisDao.get(id);
    }

    @Override
    public List<Gis> list() {
        return gisDao.list();
    }

    @Override
    public List<Gis> circle(Double longitude, Double latitude, Double radius) {
        String geometry = geometry(longitude, latitude);
        return gisDao.circle(geometry, radius);
    }

    @Override
    public List<Gis> polygon(List<Gis> gisList) {
        String geometry = geometry(gisList);
        if (geometry == null) {
            return null;
        }
        return gisDao.polygon(geometry);
    }

    private String geometry(Double longitude, Double latitude) {
        return "POINT" + "(" + longitude + " " + latitude + ")";
    }

    private String geometry(List<Gis> gisList) {
        if (gisList == null || gisList.size() <= 2) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (Gis gis : gisList) {
            list.add(gis.getLongitude() + " " + gis.getLatitude());
        }
        list.add(list.get(0));
        return "LINESTRING" + "(" + String.join(",", list) + ")";
    }
}
