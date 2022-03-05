package com.fleet.gridfs.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

public class FileInfo implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件大小
     */
    private long size;

    /**
     * 文件MD5值
     */
    private String md5;

    /**
     * 文件类型
     */
    private String contentType;

    /**
     * 大文件管理 GridFS 的 ID
     */
    private String gridfsId;

    /**
     * 上传时间
     */
    private Date date;

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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getGridfsId() {
        return gridfsId;
    }

    public void setGridfsId(String gridfsId) {
        this.gridfsId = gridfsId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
