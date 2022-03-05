/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.types.UInteger;


/**
 * 系统日志
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Log implements Serializable {

    private static final long serialVersionUID = -1950168996;

    private Long          id;
    private UInteger      userId;
    private String        name;
    private Integer       type;
    private String        log;
    private String        url;
    private String        httpMethod;
    private String        method;
    private String        params;
    private String        ip;
    private String        agent;
    private String        os;
    private String        browser;
    private UInteger      state;
    private String        error;
    private Long          millis;
    private LocalDateTime createTime;

    public Log() {}

    public Log(Log value) {
        this.id = value.id;
        this.userId = value.userId;
        this.name = value.name;
        this.type = value.type;
        this.log = value.log;
        this.url = value.url;
        this.httpMethod = value.httpMethod;
        this.method = value.method;
        this.params = value.params;
        this.ip = value.ip;
        this.agent = value.agent;
        this.os = value.os;
        this.browser = value.browser;
        this.state = value.state;
        this.error = value.error;
        this.millis = value.millis;
        this.createTime = value.createTime;
    }

    public Log(
        Long          id,
        UInteger      userId,
        String        name,
        Integer       type,
        String        log,
        String        url,
        String        httpMethod,
        String        method,
        String        params,
        String        ip,
        String        agent,
        String        os,
        String        browser,
        UInteger      state,
        String        error,
        Long          millis,
        LocalDateTime createTime
    ) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.log = log;
        this.url = url;
        this.httpMethod = httpMethod;
        this.method = method;
        this.params = params;
        this.ip = ip;
        this.agent = agent;
        this.os = os;
        this.browser = browser;
        this.state = state;
        this.error = error;
        this.millis = millis;
        this.createTime = createTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UInteger getUserId() {
        return this.userId;
    }

    public void setUserId(UInteger userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLog() {
        return this.log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAgent() {
        return this.agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getOs() {
        return this.os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return this.browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public UInteger getState() {
        return this.state;
    }

    public void setState(UInteger state) {
        this.state = state;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Long getMillis() {
        return this.millis;
    }

    public void setMillis(Long millis) {
        this.millis = millis;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Log (");

        sb.append(id);
        sb.append(", ").append(userId);
        sb.append(", ").append(name);
        sb.append(", ").append(type);
        sb.append(", ").append(log);
        sb.append(", ").append(url);
        sb.append(", ").append(httpMethod);
        sb.append(", ").append(method);
        sb.append(", ").append(params);
        sb.append(", ").append(ip);
        sb.append(", ").append(agent);
        sb.append(", ").append(os);
        sb.append(", ").append(browser);
        sb.append(", ").append(state);
        sb.append(", ").append(error);
        sb.append(", ").append(millis);
        sb.append(", ").append(createTime);

        sb.append(")");
        return sb.toString();
    }
}
