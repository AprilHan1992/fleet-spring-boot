package com.fleet.initializr;

import com.fleet.initializr.entity.Application;
import com.fleet.initializr.service.ProjectGeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitializrApplicationTests {

    @Resource
    ProjectGeneratorService projectGeneratorService;

    @Test
    public void generator() throws Exception {
        Application application = new Application();
        application.setLocation("D://code/test");
        application.setPackageName("com.fleet.test");
//        application.setApplicationName("TestApplication");
//        application.setPort("8000");
//        application.setTestApplicationName("TestApplicationTests");
        application.setGroupId("com.fleet");
        application.setArtifactId("fleet-test");
        application.setName("test");
        application.setDescription("测试");
//        application.setPackaging("jar");
//        application.setVersion("1.0");
        projectGeneratorService.generator(application);
    }
}
