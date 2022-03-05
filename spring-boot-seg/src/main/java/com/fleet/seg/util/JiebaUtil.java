package com.fleet.seg.util;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Jieba 分词器
 *
 * @author April Han
 */
public class JiebaUtil {

    public static List<String> indexSeg(String text) {
        List<String> list = new ArrayList<>();
        JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
        for (SegToken segToken : jiebaSegmenter.process(text, JiebaSegmenter.SegMode.INDEX)) {
            list.add(segToken.word);
        }
        return list;
    }

    public static List<String> searchSeg(String text) {
        List<String> list = new ArrayList<>();
        JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
        for (SegToken segToken : jiebaSegmenter.process(text, JiebaSegmenter.SegMode.SEARCH)) {
            list.add(segToken.word);
        }
        return list;
    }
}
