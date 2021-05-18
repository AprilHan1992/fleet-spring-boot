package com.fleet.initializr.service.impl;

import com.fleet.initializr.entity.Application;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author April Han
 */
@Service
public class YmlGenerator extends BaseGenerator {

    public void generation(Application application) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("port", application.getPort());

        String path = application.getLocation() + File.separator + application.getArtifactId() + "/src/main/resources/";
        File file = new File(path, "application.yml");
        super.write(file, "application.yml.ftl", map);
        file = new File(path, "application-dev.yml");
        super.write(file, "application-dev.yml.ftl", null);
        file = new File(path, "application-test.yml");
        super.write(file, "application-test.yml.ftl", null);
        file = new File(path, "application-pro.yml");
        super.write(file, "application-pro.yml.ftl", null);
    }
}
