package com.fleet.socketio.entity;

import java.io.Serializable;

public class Msg implements Serializable {

    private static final long serialVersionUID = 1L;

    public Msg(long timestamp, String msg) {
        this.timestamp = timestamp;
        this.msg = msg;
    }

    private long timestamp;

    private String msg;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
