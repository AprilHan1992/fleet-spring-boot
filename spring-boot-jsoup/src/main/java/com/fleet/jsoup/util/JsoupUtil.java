package com.fleet.jsoup.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * @author April Han
 */
public class JsoupUtil {

    private static final Whitelist whitelist = Whitelist.relaxed();

    /**
     * 配置过滤化参数，不对代码进行格式化
     */
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    static {
        /**
         * addTags() 设置白名单标签
         * addAttributes() 设置标签需要保留的属性 ,[:all]表示所有
         * preserveRelativeLinks() 是否保留元素的 URL 属性中的相对链接，或将它们转换为绝对链接，默认为false。. 为false时将会把baseUri和元素的URL属性拼接起来
         */
        whitelist.addAttributes(":all", "style");
        whitelist.preserveRelativeLinks(true);
    }

    public static String clean(String content) {
        return Jsoup.clean(content, "", whitelist, outputSettings);
    }
}
