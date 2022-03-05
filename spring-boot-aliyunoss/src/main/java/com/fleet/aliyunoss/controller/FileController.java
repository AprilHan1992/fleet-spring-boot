package com.fleet.aliyunoss.controller;

import com.fleet.aliyunoss.config.AliyunOSSConfig;
import com.fleet.aliyunoss.config.FileConfig;
import com.fleet.aliyunoss.util.AliyunOSSUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private AliyunOSSConfig aliyunOSSConfig;

    @Resource
    private FileConfig fileConfig;

    @Resource
    private AliyunOSSUtil aliyunOSSUtil;

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public void upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        File dest = new File(fileConfig.getFilePath() + Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(dest);
        fos.write(file.getBytes());
        fos.close();
        file.transferTo(dest);
        aliyunOSSUtil.upload(dest);
        dest.delete();
    }

    /**
     * 下载文件
     */
    @RequestMapping("/download/{fileName:.+}")
    public void download(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        aliyunOSSUtil.download(fileName, response);
    }

    /**
     * 删除文件
     */
    @RequestMapping("/delete/{fileName:.+}")
    public void delete(@PathVariable("fileName") String fileName) {
        aliyunOSSUtil.delete(fileName);
    }

    /**
     * 文件列表
     */
    @GetMapping("/list")
    public List<String> list() {
        return aliyunOSSUtil.list();
    }
}
