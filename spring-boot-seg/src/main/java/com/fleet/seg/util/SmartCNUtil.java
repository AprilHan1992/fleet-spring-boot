package com.fleet.seg.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * SmartCN 分词器
 *
 * @author April Han
 */
public class SmartCNUtil {

    public static List<String> seg(String text) throws Exception {
        List<String> list = new ArrayList<>();
        Analyzer analyzer = new SmartChineseAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("content", text);
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            list.add(offsetAttribute.toString());
        }
        tokenStream.end();
        return list;
    }
}
