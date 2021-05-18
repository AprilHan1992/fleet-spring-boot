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
public class PomGenerator extends BaseGenerator {

    public void generation(Application application) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("groupId", application.getGroupId());
        map.put("artifactId", application.getArtifactId());
        map.put("name", application.getName());
        map.put("description", application.getDescription());
        map.put("packaging", application.getPackaging());
        map.put("version", application.getVersion());

        String path = application.getLocation() + File.separator + application.getArtifactId() + "/";
        File file = new File(path, "pom.xml");
        super.write(file, "pom.ftl", map);
    }
}
