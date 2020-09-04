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

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    FileConfig fileConfig;

    /**
     * 上传文件
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public R upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
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
    @RequestMapping("/image/view/{fileName:.+}")
    public void imageView(@PathVariable("fileName") String fileName, HttpServletResponse response) throws Exception {
        if (fileName == null) {
            return;
        }
        FileUtil.image(fileConfig.getImgPath(), fileName, response);
    }

    /**
     * 下载文件
     */
    @RequestMapping("/download/{fileName:.+}")
    public void downLoadFile(@PathVariable("fileName") String fileName, HttpServletResponse response) throws Exception {
        FileUtil.download(fileConfig.getFilePath(), fileName, response);
    }

    /**
     * 下载图片文件
     */
    @RequestMapping("/image/download/{fileName:.+}")
    public void imageDownLoad(@PathVariable("fileName") String fileName, HttpServletResponse response) throws Exception {
        FileUtil.download(fileConfig.getImgPath(), fileName, response);
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
