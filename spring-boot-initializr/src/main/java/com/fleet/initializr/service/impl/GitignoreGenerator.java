package com.fleet.initializr.service.impl;

import com.fleet.initializr.entity.Application;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author April Han
 */
@Service
public class GitignoreGenerator extends BaseGenerator {

    public void generation(Application application) throws Exception {
        String path = application.getLocation() + File.separator + application.getArtifactId() + "/";
        File file = new File(path, ".gitignore");
        super.write(file, ".gitignore.ftl", null);
    }
}
