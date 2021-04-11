package com.fleet.webmagic.processor;

import com.fleet.webmagic.entity.Content;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽取 List 列表
 *
 * @author April Han
 */
public class ContentPageProcessor implements PageProcessor {

    public static String BASE_URL = "https://gitee.com";
    public static String PAGE_URL = "https://gitee.com/explore/program-develop";

    @Override
    public void process(Page page) {
        List<Selectable> list = page.getHtml().xpath("//div[@class='ui relaxed divided items explore-repo__list']/div[@class='item']").nodes();
        if (list != null && list.size() > 0) {
            List<Content> contentList = new ArrayList<>();
            for (Selectable selectable : list) {
                String title = selectable.xpath("//h3/a[@class='title project-namespace-path']/text()").toString();
                String url = selectable.xpath("//h3/a[@class='title project-namespace-path']/@href").toString();
                String desc = selectable.xpath("//div[@class='project-desc']/text()").toString();
                Content content = new Content();
                content.setTitle(title);
                content.setDesc(desc);
                content.setUrl(BASE_URL + url);
                contentList.add(content);
            }
            page.putField("contentList", contentList);
        }

        String[] pages = page.getUrl().toString().split("page=");
        if (pages.length == 2) {
            int p = Integer.parseInt(pages[1]);
            if (p < 10) {
                p += 1;
                page.addTargetRequest(PAGE_URL + "?page=" + p);
            }
        } else {
            page.addTargetRequest(PAGE_URL + "?page=" + 2);
        }
    }

    @Override
    public Site getSite() {
        return Site.me()
                .setDomain(PAGE_URL)
                .setSleepTime(5000)
                .setRetryTimes(0)
                .setCycleRetryTimes(0)
                .setRetrySleepTime(1000)
                .setTimeOut(5000)
                .setCharset("utf-8");
    }
}
