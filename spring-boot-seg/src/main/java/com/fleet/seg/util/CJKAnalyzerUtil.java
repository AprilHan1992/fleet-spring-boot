package com.fleet.seg.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CJKAnalyzer 分词器
 *
 * @author April Han
 */
public class CJKAnalyzerUtil {

    public static List<String> seg(String text) throws IOException {
        List<String> list = new ArrayList<>();
        Analyzer analyzer = new CJKAnalyzer();
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
