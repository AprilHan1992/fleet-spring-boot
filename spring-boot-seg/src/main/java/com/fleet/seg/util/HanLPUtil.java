package com.fleet.seg.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.*;

import java.util.ArrayList;
import java.util.List;

/**
 * HanLP 分词器
 * 使用前，需要访问 http://download.hanlp.com/，下载 data-for-1.7.5.zip，hanlp-1.8.1-release.zip 文件
 * 解压 hanlp-1.8.1-release.zip 文件后，取 hanlp.properties 文件，放在 src/main/resources 下，并且配置 'root' 参数指定存放路径
 * 解压 data-for-1.7.5.zip 文件后，取 data 文件夹，放在上一步配置的 'root' 参数指定存放路径下
 *
 * @author April Han
 */
public class HanLPUtil {

    public static List<String> seg(String text) {
        List<String> list = new ArrayList<>();
        List<Term> segList = HanLP.segment(text);
        for (Term term : segList) {
            list.add(term.word);
        }
        return list;
    }

    public static List<String> standardTokenizerSeg(String text) {
        List<String> list = new ArrayList<>();
        List<Term> segList = StandardTokenizer.segment(text);
        for (Term term : segList) {
            list.add(term.word);
        }
        return list;
    }

    public static List<String> nlpTokenizerSeg(String text) {
        List<String> list = new ArrayList<>();
        List<Term> segList = NLPTokenizer.segment(text);
        for (Term term : segList) {
            list.add(term.word);
        }
        return list;
    }

    public static List<String> indexTokenizerSeg(String text) {
        List<String> list = new ArrayList<>();
        List<Term> segList = IndexTokenizer.segment(text);
        for (Term term : segList) {
            list.add(term.word);
        }
        return list;
    }

    public static List<String> traditionalChineseTokenizerSeg(String text) {
        List<String> list = new ArrayList<>();
        List<Term> segList = TraditionalChineseTokenizer.segment(text);
        for (Term term : segList) {
            list.add(term.word);
        }
        return list;
    }

    public static List<String> speedTokenizerSeg(String text) {
        List<String> list = new ArrayList<>();
        List<Term> segList = SpeedTokenizer.segment(text);
        for (Term term : segList) {
            list.add(term.word);
        }
        return list;
    }

    public static List<String> nShortSegmentSeg(String text) {
        List<String> list = new ArrayList<>();
        Segment nShortSegment = new NShortSegment()
                .enableCustomDictionary(false)
                .enablePlaceRecognize(true)
                .enableOrganizationRecognize(true);
        List<Term> segList = nShortSegment.seg(text);
        for (Term term : segList) {
            list.add(term.word);
        }
        return list;
    }

    public static List<String> viterbiSegmentSeg(String text) {
        List<String> list = new ArrayList<>();
        Segment viterbiSegment = new ViterbiSegment()
                .enableCustomDictionary(false)
                .enablePlaceRecognize(true)
                .enableOrganizationRecognize(true);
        List<Term> segList = viterbiSegment.seg(text);
        for (Term term : segList) {
            list.add(term.word);
        }
        return list;
    }

    public static List<String> dijkstraSegmentSeg(String text) {
        List<String> list = new ArrayList<>();
        Segment dijkstraSegment = new DijkstraSegment()
                .enableCustomDictionary(false)
                .enablePlaceRecognize(true)
                .enableOrganizationRecognize(true);
        List<Term> segList = dijkstraSegment.seg(text);
        for (Term term : segList) {
            list.add(term.word);
        }
        return list;
    }

    public static List<String> crfLexicalAnalyzerSeg(String text) throws Exception {
        List<String> list = new ArrayList<>();
        CRFLexicalAnalyzer crfLexicalAnalyzer = new CRFLexicalAnalyzer();
        List<Term> segList = crfLexicalAnalyzer.seg(text);
        for (Term term : segList) {
            list.add(term.word);
        }
        return list;
    }
}
