package com.fleet.jpush.entity;

import java.io.Serializable;
import java.util.Map;

public class JPush implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 目标平台: "all"，"android"，"ios"，"winphone"
     */
    private String platform;

    private String title;

    private String alert;

    private Map<String, String> extras;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }
}
