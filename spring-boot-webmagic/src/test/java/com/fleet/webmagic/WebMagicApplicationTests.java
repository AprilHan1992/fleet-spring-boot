package com.fleet.webmagic;

import com.fleet.webmagic.pipeline.ContentPipeline;
import com.fleet.webmagic.processor.ContentPageProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebMagicApplicationTests {

    public static String PAGE_URL = "https://gitee.com/explore/program-develop";

    @Resource
    ContentPipeline contentPipeline;

    @Test
    public void spider() {
        ContentPageProcessor contentPageProcessor = new ContentPageProcessor();
        Spider spider = Spider.create(contentPageProcessor);
        spider.addUrl(PAGE_URL);
        spider.addPipeline(contentPipeline);
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.run();
    }
}
