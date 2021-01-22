package com.fleet.japidocs;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JApiDocsApplicationTests {

    @Test
    public void testBuildApi() {
        DocsConfig config = new DocsConfig();
        // 项目根目录
        config.setProjectPath(System.getProperty("user.dir"));
        // 项目名称
        config.setProjectName("JApiDocs API文档");
        // API 文档版本
        config.setApiVersion("V1.0");
        // API 文档生成目录
        config.setDocsPath("src/main/resources/static/doc");
        // 配置自动生成
        config.setAutoGenerate(Boolean.TRUE);
        // 执行生成文档
        Docs.buildHtmlDocs(config);
    }
}
