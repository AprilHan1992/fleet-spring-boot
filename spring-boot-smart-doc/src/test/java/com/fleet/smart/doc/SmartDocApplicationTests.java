package com.fleet.smart.doc;

import com.power.doc.builder.HtmlApiDocBuilder;
import com.power.doc.constants.DocGlobalConstants;
import com.power.doc.model.ApiConfig;
import com.power.doc.model.ApiDataDictionary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartDocApplicationTests {

    @Test
    public void testBuildApi() {
        ApiConfig config = new ApiConfig();
        // 项目名称
        config.setProjectName("Smart-Doc API文档");
        // 服务器地址
        config.setServerUrl("http://localhost:8000");
        // 采用严格模式
        config.setStrict(false);
        // 所有接口文档合并生成到一个文档
        config.setAllInOne(true);
        // 文档的输出路径
        config.setOutPath(DocGlobalConstants.HTML_DOC_OUT_PATH);
        config.setCoverOld(true);

        ApiDataDictionary dataDictionary = new ApiDataDictionary();
        dataDictionary.setTitle("http状态码");
        dataDictionary.setEnumClassName("com.fleet.smart.doc.enums.ResultState");
        dataDictionary.setCodeField("code");
        dataDictionary.setDescField("msg");
        config.setDataDictionaries(dataDictionary);

        HtmlApiDocBuilder.buildApiDoc(config);
    }
}
