package com.fleet.postgis.entity;

import java.io.Serializable;

/**
 * @author April Han
 */
public class Gis implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 地图元素点的名称（医院，银行，警察局等）
     */
    private String name;

    /**
     * 地图元素点的经度
     */
    private Double longitude;

    /**
     * 地图元素点的的纬度
     */
    private Double latitude;

    /**
     * 空间信息转换的字符串
     */
    private String geometry;

    public Gis() {
    }

    public Gis(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Gis(String name, Double longitude, Double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }
}
