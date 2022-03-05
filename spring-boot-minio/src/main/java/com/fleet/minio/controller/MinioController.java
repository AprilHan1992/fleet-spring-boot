package com.fleet.minio.controller;

import com.fleet.minio.entity.MinioFile;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/minio")
public class MinioController {

    @Value("${minio.endpoint}")
    private String ENDPOINT;

    @Value("${minio.bucket-name}")
    private String BUCKET_NAME;

    @Value("${minio.access-key}")
    private String ACCESS_KEY;

    @Value("${minio.secret-key}")
    private String SECRET_KEY;

    @RequestMapping(value = "/upload")
    public MinioFile upload(@RequestParam("file") MultipartFile file) throws Exception {
        MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
        if (!minioClient.bucketExists(BUCKET_NAME)) {
            minioClient.makeBucket(BUCKET_NAME);
        }

        minioClient.putObject(BUCKET_NAME, file.getOriginalFilename(), file.getInputStream(), null, null, null, file.getContentType());

        MinioFile minioFile = new MinioFile();
        minioFile.setName(file.getOriginalFilename());
        minioFile.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + file.getOriginalFilename());
        return minioFile;
    }

    /**
     * 下载文件
     */
    @RequestMapping("/download/{name:.+}")
    public void download(@PathVariable("name") String name, HttpServletResponse response) throws Exception {
        MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
        ObjectStat stat = minioClient.statObject(BUCKET_NAME, name);
        response.reset();
        response.setContentType(stat.contentType());
        response.setHeader("Content-Disposition", "attachment; filename=" + new String(name.getBytes(), StandardCharsets.ISO_8859_1));
        InputStream is = minioClient.getObject(BUCKET_NAME, name);
        IOUtils.copy(is, response.getOutputStream());
        is.close();
    }

    @RequestMapping(value = "/delete/{name:.+}")
    public void delete(@PathVariable("name") String name) throws Exception {
        MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
        minioClient.removeObject(BUCKET_NAME, name);
    }
}
