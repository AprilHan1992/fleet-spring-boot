package com.fleet.libre.office;

import org.jodconverter.core.DocumentConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibreOfficeApplicationTests {

    @Resource
    private DocumentConverter converter;

    @Test
    public void Office2Pdf() throws Exception {
        File inputFile = new File("D:/test.doc");
        File outputFile = new File("D:/test.pdf");
        converter.convert(inputFile).to(outputFile).execute();
    }
}
