package com.fleet.postgis.service;

import com.fleet.postgis.entity.Gis;

import java.util.List;

/**
 * @author April Han
 */
public interface GisService {

    int insert(Gis gis);

    Gis get(Long id);

    List<Gis> list();

    List<Gis> circle(Double longitude, Double latitude, Double radius);

    List<Gis> polygon(List<Gis> gisList);
}
