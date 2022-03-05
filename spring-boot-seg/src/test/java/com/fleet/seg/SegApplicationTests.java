package com.fleet.seg;

import com.alibaba.fastjson.JSON;
import com.fleet.seg.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SegApplicationTests {

    @Test
    public void WordUtilTest() {
        String text = "这段话是中文分词测试";
        List<String> list = WordUtil.seg(text);
        System.out.println(JSON.toJSONString(list));

        list = WordUtil.segWithStopWords(text);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void AnsjUtilTest() {
        String text = "这段话是中文分词测试";
        List<String> list = AnsjUtil.baseAnalysisSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = AnsjUtil.dicAnalysisSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = AnsjUtil.toAnalysisSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = AnsjUtil.nlpAnalysisSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = AnsjUtil.indexAnalysisSeg(text);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void StanfordUtilTest() {
        String text = "这段话是中文分词测试";
        List<String> list = StanfordUtil.seg(text);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void JiebaUtilTest() throws Exception {
        String text = "这段话是中文分词测试";
        List<String> list = JiebaUtil.indexSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = JiebaUtil.searchSeg(text);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void JcsegUtilTest() throws Exception {
        String text = "这段话是中文分词测试";
        List<String> list = JcsegUtil.simpleSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = JcsegUtil.complexSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = JcsegUtil.detectSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = JcsegUtil.searchSeg(text);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void MMSeg4jUtilTest() throws Exception {
        String text = "这段话是中文分词测试";
        List<String> list = MMSeg4jUtil.simpleSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = MMSeg4jUtil.complexSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = MMSeg4jUtil.maxWordSeg(text);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void IKAnalyzerUtilTest() throws Exception {
        String text = "这段话是中文分词测试";
        List<String> list = IKAnalyzerUtil.seg(text);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void CJKAnalyzerUtilTest() throws Exception {
        String text = "这段话是中文分词测试";
        List<String> list = CJKAnalyzerUtil.seg(text);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void SmartCNUtilTest() throws Exception {
        String text = "这段话是中文分词测试";
        List<String> list = SmartCNUtil.seg(text);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void HanLPUtilTest() throws Exception {
        String text = "这段话是中文分词测试";
        List<String> list = HanLPUtil.seg(text);
        System.out.println(JSON.toJSONString(list));

        list = HanLPUtil.standardTokenizerSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = HanLPUtil.nlpTokenizerSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = HanLPUtil.indexTokenizerSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = HanLPUtil.traditionalChineseTokenizerSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = HanLPUtil.speedTokenizerSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = HanLPUtil.nShortSegmentSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = HanLPUtil.viterbiSegmentSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = HanLPUtil.dijkstraSegmentSeg(text);
        System.out.println(JSON.toJSONString(list));

        list = HanLPUtil.crfLexicalAnalyzerSeg(text);
        System.out.println(JSON.toJSONString(list));
    }
}
