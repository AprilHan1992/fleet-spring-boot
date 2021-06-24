package com.fleet.file.controller;

import com.fleet.file.config.FileConfig;
import com.fleet.file.entity.FileInfo;
import com.fleet.file.json.R;
import com.fleet.file.util.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    FileConfig fileConfig;

    /**
     * 上传文件
     */
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public R fileUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file == null) {
            return R.error("上传文件为空");
        }
        try {
            String newFilename = FileUtil.rename(file.getOriginalFilename());
            FileInfo fileInfo = new FileInfo();
            fileInfo.setOriginalFilename(file.getOriginalFilename());
            fileInfo.setNewFilename(newFilename);
            fileInfo.setDir(fileConfig.getFilePath());
            fileInfo.setPath(fileConfig.getFilePath() + newFilename);
            fileInfo.setSize(file.getSize());
            FileUtil.upload(file, fileConfig.getFilePath(), newFilename);
            return R.ok(fileInfo);
        } catch (Exception e) {
            return R.error("失败");
        }
    }

    /**
     * 上传多个文件
     */
    @RequestMapping(value = "/files/upload", method = RequestMethod.POST)
    public R filesUploads(@RequestParam(value = "files") MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return R.error("上传文件为空");
        }
        try {
            List<FileInfo> list = new ArrayList<>();
            for (MultipartFile file : files) {
                String newFilename = FileUtil.rename(file.getOriginalFilename());
                FileInfo fileInfo = new FileInfo();
                fileInfo.setOriginalFilename(file.getOriginalFilename());
                fileInfo.setNewFilename(newFilename);
                fileInfo.setDir(fileConfig.getFilePath());
                fileInfo.setPath(fileConfig.getFilePath() + newFilename);
                fileInfo.setSize(file.getSize());
                FileUtil.upload(file, fileConfig.getFilePath(), newFilename);
                list.add(fileInfo);
            }
            return R.ok(list);
        } catch (Exception e) {
            return R.error("失败");
        }
    }

    /**
     * 上传图片文件
     */
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public R imageUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file == null) {
            return R.error("上传图片文件为空");
        }
        try {
            String newFilename = FileUtil.rename(file.getOriginalFilename());
            FileUtil.upload(file, fileConfig.getImgPath(), newFilename);
            return R.ok("/file/image/view/" + newFilename);
        } catch (Exception e) {
            return R.error("失败");
        }
    }

    /**
     * 查看图片
     */
    @RequestMapping("/image/view/{filename:.+}")
    public void imageView(@PathVariable("filename") String filename, HttpServletResponse response) throws Exception {
        if (filename == null) {
            return;
        }
        FileUtil.image(fileConfig.getImgPath(), filename, response);
    }

    /**
     * 下载文件
     */
    @RequestMapping("/download/{filename:.+}")
    public void downLoadFile(@PathVariable("filename") String filename, HttpServletResponse response) throws Exception {
        FileUtil.download(fileConfig.getFilePath(), filename, response);
    }

    /**
     * 下载图片文件
     */
    @RequestMapping("/image/download/{filename:.+}")
    public void imageDownLoad(@PathVariable("filename") String filename, HttpServletResponse response) throws Exception {
        FileUtil.download(fileConfig.getImgPath(), filename, response);
    }

    /**
     * 批量下载文件
     */
    @RequestMapping("/download")
    public void downLoadFiles(HttpServletResponse response) throws Exception {
        File[] files = new File[3];
        files[0] = new File(fileConfig.getImgPath() + "1.jpg");
        files[1] = new File(fileConfig.getImgPath() + "2.jpg");
        files[2] = new File(fileConfig.getImgPath());
        FileUtil.download(fileConfig.getFilePath(), files, "图片.zip", response);
    }
}
