package com.fleet.initializr.service.impl;

import com.fleet.initializr.entity.Application;
import com.fleet.initializr.service.ProjectGeneratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author April Han
 */
@Service
public class ProjectGeneratorServiceImpl implements ProjectGeneratorService {

    @Resource
    private ApplicationGenerator applicationGenerator;

    @Resource
    private YmlGenerator ymlGenerator;

    @Resource
    private GitignoreGenerator gitignoreGenerator;

    @Resource
    private PomGenerator pomGenerator;

    @Resource
    private TestApplicationGenerator testApplicationGenerator;

    @Resource
    private LogbackSpringGenerator logbackSpringGenerator;

    @Override
    public void generator(Application application) throws Exception {
        File file = new File(application.getLocation() + File.separator + application.getArtifactId());
        if (!file.exists()) {
            if (file.getParentFile().exists() && !file.getParentFile().isDirectory()) {
                throw new Exception("目标地址不是文件夹");
            } else {
                if (!file.getParentFile().exists()) {
                    if (!file.getParentFile().mkdirs()) {
                        throw new Exception("创建目标地址文件夹失败");
                    }
                }
            }

            if (!file.mkdirs()) {
                throw new Exception("创建目标文件夹失败");
            }
        } else {
            if (!file.isDirectory()) {
                throw new Exception("目标不是文件夹");
            }
        }

        if (StringUtils.isEmpty(application.getApplicationName())) {
            application.setApplicationName(application.getName().substring(0, 1).toUpperCase() + application.getName().substring(1) + "Application");
        }

        if (StringUtils.isEmpty(application.getPort())) {
            application.setPort("80");
        }

        if (StringUtils.isEmpty(application.getTestApplicationName())) {
            application.setTestApplicationName(application.getApplicationName() + "Tests");
        }

        if (StringUtils.isEmpty(application.getPackaging())) {
            application.setPackaging("jar");
        }

        if (StringUtils.isEmpty(application.getVersion())) {
            application.setVersion("1.0");
        }

        // 1、创建  Application.java
        applicationGenerator.generation(application);

        // 2、创建 .yml 配置文件
        ymlGenerator.generation(application);

        // 3、创建 .gitignore 配置文件
        gitignoreGenerator.generation(application);

        // 4. 创建 pom.xml
        pomGenerator.generation(application);

        // 5、创建  ApplicationTests.java
        testApplicationGenerator.generation(application);

        // 6. 创建 logback-spring.xml
        logbackSpringGenerator.generation(application);
    }
}
