package com.fleet.seg.util;

import com.chenlb.mmseg4j.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * MMSeg4j 分词器
 *
 * @author April Han
 */
public class MMSeg4jUtil {

    public static List<String> simpleSeg(String text) throws Exception {
        List<String> list = new ArrayList<>();
        Dictionary dictionary = Dictionary.getInstance();
        MMSeg mmSeg = new MMSeg(new StringReader(text), new SimpleSeg(dictionary));
        Word word;
        while ((word = mmSeg.next()) != null) {
            list.add(word.getString());
        }
        return list;
    }

    public static List<String> complexSeg(String text) throws Exception {
        List<String> list = new ArrayList<>();
        Dictionary dictionary = Dictionary.getInstance();
        MMSeg mmSeg = new MMSeg(new StringReader(text), new ComplexSeg(dictionary));
        Word word;
        while ((word = mmSeg.next()) != null) {
            list.add(word.getString());
        }
        return list;
    }

    public static List<String> maxWordSeg(String text) throws Exception {
        List<String> list = new ArrayList<>();
        Dictionary dictionary = Dictionary.getInstance();
        MMSeg mmSeg = new MMSeg(new StringReader(text), new MaxWordSeg(dictionary));
        Word word;
        while ((word = mmSeg.next()) != null) {
            list.add(word.getString());
        }
        return list;
    }
}
