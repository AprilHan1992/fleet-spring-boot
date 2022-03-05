package com.fleet.initializr.service.impl;

import com.fleet.initializr.entity.Application;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author April Han
 */
@Service
public class LogbackSpringGenerator extends BaseGenerator {

    public void generation(Application application) throws Exception {
        String path = application.getLocation() + File.separator + application.getArtifactId() + "/src/main/resources/";
        File file = new File(path, "logback-spring.xml");
        super.write(file, "logback-spring.xml.ftl", null);
    }
}
