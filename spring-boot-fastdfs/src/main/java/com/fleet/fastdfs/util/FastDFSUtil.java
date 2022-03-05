package com.fleet.fastdfs.util;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FastDFSUtil {

    @Resource
    private FastFileStorageClient storageClient;

    /**
     * 上传文件
     */
    public String uploadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        StorePath storePath = storageClient.uploadFile(is, file.length(), FilenameUtils.getExtension(file.getName()), null);
        return storePath.getFullPath();
    }

    /**
     * 上传文件
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return storePath.getFullPath();
    }

    /**
     * 上传文件
     */
    public String uploadFile(InputStream is, String fileExtName) throws IOException {
        StorePath storePath = storageClient.uploadFile(is, is.available(), fileExtName, null);
        return storePath.getFullPath();
    }

    /**
     * 上传图片文件
     */
    public String uploadImageFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(is, file.length(), FilenameUtils.getExtension(file.getName()), null);
        return storePath.getFullPath();
    }

    /**
     * 上传图片文件
     */
    public String uploadImageFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return storePath.getFullPath();
    }

    /**
     * 上传图片文件
     */
    public String uploadImageFile(InputStream is, String fileExtName) throws IOException {
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(is, is.available(), fileExtName, null);
        return storePath.getFullPath();
    }

    /**
     * 下载文件
     */
    public byte[] downloadFile(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        StorePath storePath = StorePath.parseFromUrl(filePath);
        return storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
    }

    /**
     * 删除文件
     */
    public void deleteFile(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return;
        }
        StorePath storePath = StorePath.parseFromUrl(filePath);
        storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
    }
}
