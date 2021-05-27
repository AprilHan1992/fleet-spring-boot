package com.fleet.file.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @author April Han
 */
@Configuration
public class MultipartConfig {

    /**
     * 文件上传临时路径，防止出现 java.io.IOException: The temporary upload location [ *** ] is not valid 错误
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = System.getProperty("user.dir") + "/tmp";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        // 上传的单个文件大小
        factory.setMaxFileSize(DataSize.parse("100MB"));
        // 上传的总文件大小
        factory.setMaxRequestSize(DataSize.parse("200MB"));
        return factory.createMultipartConfig();
    }
}
