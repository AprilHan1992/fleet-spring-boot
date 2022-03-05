package com.fleet.influxdb.entity;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * @author April Han
 */
@Measurement(name = "user")
public class User {

    @Column(name = "time")
    private Instant time;

    @Column(name = "id", tag = true)
    private String id;

    @Column(name = "name", tag = true)
    private String name;

    @Column(name = "regTime")
    private String regTime;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }
}
