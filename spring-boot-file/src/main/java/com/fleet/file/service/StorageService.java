package com.fleet.file.service;

import com.fleet.file.entity.MultipartFileParam;

/**
 * @author April Han
 */
public interface StorageService {

    void uploadFileByRandomAccessFile(MultipartFileParam param) throws Exception;

    void uploadFileByMappedByteBuffer(MultipartFileParam param) throws Exception;
}
