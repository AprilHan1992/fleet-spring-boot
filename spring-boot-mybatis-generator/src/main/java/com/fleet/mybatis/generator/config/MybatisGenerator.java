package com.fleet.mybatis.generator.config;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author April Han
 */
public class MybatisGenerator {

    private static final Logger logger = LoggerFactory.getLogger(MybatisGenerator.class);

    public static void main(String[] args) throws Exception {
        // 告警信息
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);

        // 读取 MBG 配置文件
        InputStream is = MybatisGenerator.class.getResourceAsStream("/mybatis-generator.xml");
        Configuration config = cp.parseConfiguration(is);
        is.close();

        // 当生成的代码重复时，覆盖原代码
        boolean overwrite = true;
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        // 输出告警信息
        for (String warning : warnings) {
            logger.warn("【告警】：{}", warning);
        }
    }
}
