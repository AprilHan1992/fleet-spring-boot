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
public class ApplicationGenerator extends BaseGenerator {

    public void generation(Application application) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("packageName", application.getPackageName());
        map.put("applicationName", application.getApplicationName());

        String path = application.getLocation() + File.separator + application.getArtifactId() + "/src/main/java/" + application.getPackageName().replace(".", File.separator) + File.separator;
        File file = new File(path, application.getApplicationName() + ".java");
        super.write(file, "application.ftl", map);
    }
}
