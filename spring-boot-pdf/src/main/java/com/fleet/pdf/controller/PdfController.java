package com.fleet.pdf.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @RequestMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        Document document = new Document(PageSize.A4);
        File file = new File("D:\\demo.pdf");
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        // 打开文件
        document.open();

        // 设置属性
        // 标题
        document.addTitle("标题");
        // 作者
        document.addAuthor("作者");
        // 主题
        document.addSubject("主题");
        // 关键字
        document.addKeywords("关键字");
        // 创建时间
        document.addCreationDate();
        // 应用程序
        document.addCreator("fleetsoft.com");

        document.add(new Paragraph("Hello World"));

        document.close();
        writer.close();
    }
}
