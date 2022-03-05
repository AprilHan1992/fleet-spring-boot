package com.fleet.seg.util;

import org.lionsoul.jcseg.tokenizer.core.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Jcseg 分词器
 *
 * @author April Han
 */
public class JcsegUtil {

    public static List<String> simpleSeg(String text) throws Exception {
        List<String> list = new ArrayList<>();
        JcsegTaskConfig jcsegTaskConfig = new JcsegTaskConfig();
        jcsegTaskConfig.setLoadCJKSyn(false);
        jcsegTaskConfig.setLoadCJKPinyin(false);
        ADictionary dictionary = DictionaryFactory.createDefaultDictionary(jcsegTaskConfig);
        ISegment seg = SegmentFactory.createJcseg(JcsegTaskConfig.SIMPLE_MODE, new StringReader(text), jcsegTaskConfig, dictionary);
        IWord word;
        while ((word = seg.next()) != null) {
            list.add(word.getValue());
        }
        return list;
    }

    public static List<String> complexSeg(String text) throws Exception {
        List<String> list = new ArrayList<>();
        JcsegTaskConfig jcsegTaskConfig = new JcsegTaskConfig();
        jcsegTaskConfig.setLoadCJKSyn(false);
        jcsegTaskConfig.setLoadCJKPinyin(false);
        ADictionary dictionary = DictionaryFactory.createDefaultDictionary(jcsegTaskConfig);
        ISegment seg = SegmentFactory.createJcseg(JcsegTaskConfig.COMPLEX_MODE, new StringReader(text), jcsegTaskConfig, dictionary);
        IWord word;
        while ((word = seg.next()) != null) {
            list.add(word.getValue());
        }
        return list;
    }

    public static List<String> detectSeg(String text) throws Exception {
        List<String> list = new ArrayList<>();
        JcsegTaskConfig jcsegTaskConfig = new JcsegTaskConfig();
        jcsegTaskConfig.setLoadCJKSyn(false);
        jcsegTaskConfig.setLoadCJKPinyin(false);
        ADictionary dictionary = DictionaryFactory.createDefaultDictionary(jcsegTaskConfig);
        ISegment seg = SegmentFactory.createJcseg(JcsegTaskConfig.DETECT_MODE, new StringReader(text), jcsegTaskConfig, dictionary);
        IWord word;
        while ((word = seg.next()) != null) {
            list.add(word.getValue());
        }
        return list;
    }

    public static List<String> searchSeg(String text) throws Exception {
        List<String> list = new ArrayList<>();
        JcsegTaskConfig jcsegTaskConfig = new JcsegTaskConfig();
        jcsegTaskConfig.setLoadCJKSyn(false);
        jcsegTaskConfig.setLoadCJKPinyin(false);
        ADictionary dictionary = DictionaryFactory.createDefaultDictionary(jcsegTaskConfig);
        ISegment seg = SegmentFactory.createJcseg(JcsegTaskConfig.SEARCH_MODE, new StringReader(text), jcsegTaskConfig, dictionary);
        IWord word;
        while ((word = seg.next()) != null) {
            list.add(word.getValue());
        }
        return list;
    }
}
