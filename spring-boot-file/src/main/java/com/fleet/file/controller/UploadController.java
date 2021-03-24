package com.fleet.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author April Han
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @GetMapping("/file")
    public String file() {
        return "file";
    }

    @GetMapping("/image")
    public String image() {
        return "image";
    }

    @GetMapping("/bigFile")
    public String bigFile() {
        return "bigFile";
    }
}
