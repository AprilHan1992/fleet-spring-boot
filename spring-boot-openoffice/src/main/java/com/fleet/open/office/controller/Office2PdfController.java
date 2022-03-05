package com.fleet.open.office.controller;

import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.office.OfficeException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping("/open/office")
public class Office2PdfController {

    @Resource
    private DocumentConverter converter;

    @RequestMapping(value = "/office2Pdf")
    public void office2Pdf() throws OfficeException {
        File src = new File("D:/test.doc");
        File dest = new File("D:/test.pdf");
        // 判断源文件是否存在
        if (!src.exists() && !src.isFile()) {
            throw new RuntimeException("源文件不存在");
        }
        // 判断源文件是否存在
        if (dest.exists() && dest.isFile()) {
            throw new RuntimeException("目标文件已存在");
        }
        // 判断目标文件目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        if (converter != null) {
            converter.convert(src).to(dest).execute();
        }
    }
}
