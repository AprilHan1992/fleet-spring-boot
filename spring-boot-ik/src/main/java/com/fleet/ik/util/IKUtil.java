package com.fleet.ik.util;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author April Han
 */
public class IKUtil {

    public static List<String> participle(String string) throws IOException {
        StringReader stringReader = new StringReader(string);
        IKSegmenter ikSegmenter = new IKSegmenter(stringReader, true);
        Lexeme lexeme;
        List<String> list = new ArrayList<>();
        while ((lexeme = ikSegmenter.next()) != null) {
            list.add(lexeme.getLexemeText());
        }
        return list;
    }
}
