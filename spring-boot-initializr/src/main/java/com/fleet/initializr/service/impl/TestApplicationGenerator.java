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
public class TestApplicationGenerator extends BaseGenerator {

    public void generation(Application application) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("packageName", application.getPackageName());
        map.put("testApplicationName", application.getTestApplicationName());

        String path = application.getLocation() + File.separator + application.getArtifactId() + "/src/test/java/" + application.getPackageName().replace(".", File.separator) + File.separator;
        File file = new File(path, application.getTestApplicationName() + ".java");
        super.write(file, "testApplication.ftl", map);
    }
}
