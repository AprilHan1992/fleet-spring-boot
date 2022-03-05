package com.fleet.aliyunoss.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.fleet.aliyunoss.config.AliyunOSSConfig;
import com.fleet.aliyunoss.config.FileConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class AliyunOSSUtil {

    private static final Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);

    @Resource
    private AliyunOSSConfig aliyunOSSConfig;

    @Resource
    private FileConfig fileConfig;

    /**
     * 上传文件
     */
    public void upload(File file) {
        String endpoint = aliyunOSSConfig.getEndpoint();
        String accessKeyId = aliyunOSSConfig.getAccessKeyId();
        String accessKeySecret = aliyunOSSConfig.getAccessKeySecret();
        String bucketName = aliyunOSSConfig.getBucketName();
        String fileHost = aliyunOSSConfig.getFileHost();

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        String key = fileHost + "/" + file.getName();
        ossClient.putObject(bucketName, key, file);
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        ossClient.shutdown();
    }

    /**
     * 下载文件
     */
    public void download(String fileName, HttpServletResponse response) throws IOException {
        String endpoint = aliyunOSSConfig.getEndpoint();
        String accessKeyId = aliyunOSSConfig.getAccessKeyId();
        String accessKeySecret = aliyunOSSConfig.getAccessKeySecret();
        String bucketName = aliyunOSSConfig.getBucketName();
        String fileHost = aliyunOSSConfig.getFileHost();

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            return;
        }

        String key = fileHost + "/" + fileName;
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        File file = new File(fileConfig.getFilePath() + fileName);
        ossClient.getObject(getObjectRequest, file);
        ossClient.shutdown();

        FileInputStream fis = new FileInputStream(file);

        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), StandardCharsets.ISO_8859_1));
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = fis.read(b)) > 0) {
            os.write(b, 0, len);
        }
        os.flush();
        os.close();
        fis.close();
    }

    /**
     * 删除文件
     */
    public void delete(String fileName) {
        String endpoint = aliyunOSSConfig.getEndpoint();
        String accessKeyId = aliyunOSSConfig.getAccessKeyId();
        String accessKeySecret = aliyunOSSConfig.getAccessKeySecret();
        String bucketName = aliyunOSSConfig.getBucketName();
        String fileHost = aliyunOSSConfig.getFileHost();

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            return;
        }

        String key = fileHost + "/" + fileName;
        ossClient.deleteObject(bucketName, key);
        ossClient.shutdown();
    }

    /**
     * 文件列表
     */
    public List<String> list() {
        String endpoint = aliyunOSSConfig.getEndpoint();
        String accessKeyId = aliyunOSSConfig.getAccessKeyId();
        String accessKeySecret = aliyunOSSConfig.getAccessKeySecret();
        String bucketName = aliyunOSSConfig.getBucketName();
        String fileHost = aliyunOSSConfig.getFileHost();

        List<String> rList = new ArrayList<>();

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
        listObjectsRequest.setPrefix(fileHost + "/");
        ObjectListing objectListing = ossClient.listObjects(listObjectsRequest);
        if (objectListing != null) {
            for (OSSObjectSummary ossObjectSummary : objectListing.getObjectSummaries()) {
                System.out.println(ossObjectSummary.getKey());
                rList.add(ossObjectSummary.getKey());
            }
        }

        return rList;
    }
}
