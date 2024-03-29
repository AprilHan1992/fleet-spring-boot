/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.types.UInteger;


/**
 * 角色
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class To implements Serializable {

    private static final long serialVersionUID = -243187826;

    private UInteger      id;
    private UInteger      msgId;
    private UInteger      to;
    private UInteger      readState;
    private LocalDateTime readTime;
    private UInteger      deleted;

    public To() {}

    public To(To value) {
        this.id = value.id;
        this.msgId = value.msgId;
        this.to = value.to;
        this.readState = value.readState;
        this.readTime = value.readTime;
        this.deleted = value.deleted;
    }

    public To(
        UInteger      id,
        UInteger      msgId,
        UInteger      to,
        UInteger      readState,
        LocalDateTime readTime,
        UInteger      deleted
    ) {
        this.id = id;
        this.msgId = msgId;
        this.to = to;
        this.readState = readState;
        this.readTime = readTime;
        this.deleted = deleted;
    }

    public UInteger getId() {
        return this.id;
    }

    public void setId(UInteger id) {
        this.id = id;
    }

    public UInteger getMsgId() {
        return this.msgId;
    }

    public void setMsgId(UInteger msgId) {
        this.msgId = msgId;
    }

    public UInteger getTo() {
        return this.to;
    }

    public void setTo(UInteger to) {
        this.to = to;
    }

    public UInteger getReadState() {
        return this.readState;
    }

    public void setReadState(UInteger readState) {
        this.readState = readState;
    }

    public LocalDateTime getReadTime() {
        return this.readTime;
    }

    public void setReadTime(LocalDateTime readTime) {
        this.readTime = readTime;
    }

    public UInteger getDeleted() {
        return this.deleted;
    }

    public void setDeleted(UInteger deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("To (");

        sb.append(id);
        sb.append(", ").append(msgId);
        sb.append(", ").append(to);
        sb.append(", ").append(readState);
        sb.append(", ").append(readTime);
        sb.append(", ").append(deleted);

        sb.append(")");
        return sb.toString();
    }
}
