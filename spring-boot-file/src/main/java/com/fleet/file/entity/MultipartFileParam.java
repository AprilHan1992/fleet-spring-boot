package com.fleet.file.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author April Han
 */
public class MultipartFileParam {

    /**
     * 文件传输任务id
     */
    private String id;

    /**
     * 总分片数量
     */
    private long chunks;

    /**
     * 当前为第几分片
     */
    private long chunk;

    /**
     * 分片大小
     */
    private long chunkSize;

    /**
     * 当前分片大小
     */
    private long size = 0L;

    /**
     * 文件名
     */
    private String name;

    /**
     * 分块文件传输对象
     */
    private MultipartFile file;

    /**
     * MD5
     */
    private String md5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getChunks() {
        return chunks;
    }

    public void setChunks(long chunks) {
        this.chunks = chunks;
    }

    public long getChunk() {
        return chunk;
    }

    public void setChunk(long chunk) {
        this.chunk = chunk;
    }

    public long getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(long chunkSize) {
        this.chunkSize = chunkSize;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
