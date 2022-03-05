package com.fleet.batch.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class User {

    @Max(3)
    private String id;

    @Size(min = 2, max = 8)
    private String name;

    private String gender;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
