package com.fleet.file.entity;

/**
 * 文件信息
 *
 * @author April Han
 */
public class FileInfo {

    /**
     * 原文件名
     */
    private String originalFilename;

    /**
     * 新文件名
     */
    private String newFilename;

    /**
     * 文件目录
     */
    private String dir;

    /**
     * 文件地址
     */
    private String path;

    /**
     * 文件大小
     */
    private Long size;

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getNewFilename() {
        return newFilename;
    }

    public void setNewFilename(String newFilename) {
        this.newFilename = newFilename;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
