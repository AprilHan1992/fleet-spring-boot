package com.fleet.seg.util;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Word 分词器
 *
 * @author April Han
 */
public class WordUtil {

    public static List<String> seg(String text) {
        List<String> list = new ArrayList<>();
        List<Word> segList = WordSegmenter.seg(text);
        for (Word word : segList) {
            list.add(word.getText());
        }
        return list;
    }

    public static List<String> segWithStopWords(String text) {
        List<String> list = new ArrayList<>();
        List<Word> segList = WordSegmenter.segWithStopWords(text);
        for (Word word : segList) {
            list.add(word.getText());
        }
        return list;
    }
}
