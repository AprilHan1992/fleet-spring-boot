package com.fleet.seg.util;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * IKAnalyzer 分词器
 *
 * @author April Han
 */
public class IKAnalyzerUtil {

    public static List<String> seg(String text) throws IOException {
        List<String> list = new ArrayList<>();
        IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(text), true);
        Lexeme lexeme;
        while ((lexeme = ikSegmenter.next()) != null) {
            list.add(lexeme.getLexemeText());
        }
        return list;
    }
}
