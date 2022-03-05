package com.fleet.jpush.config;

import cn.jpush.api.JPushClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author April Han
 */
@Component
@ConfigurationProperties(prefix = "jpush")
public class JPushConfig {

    private String appKey;

    private String masterSecret;

    private String liveTime;

    private JPushClient jPushClient = null;

    @PostConstruct
    public void initJPushClient() {
        jPushClient = new JPushClient(masterSecret, appKey);
    }

    public JPushClient getJPushClient() {
        return jPushClient;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }
}
