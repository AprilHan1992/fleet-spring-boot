package com.fleet.seg.util;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Ansj 分词器
 *
 * @author April Han
 */
public class AnsjUtil {

    public static List<String> baseAnalysisSeg(String text) {
        List<String> list = new ArrayList<>();
        Result result = BaseAnalysis.parse(text);
        for (Term term : result) {
            list.add(term.getName());
        }
        return list;
    }

    public static List<String> dicAnalysisSeg(String text) {
        List<String> list = new ArrayList<>();
        Result result = DicAnalysis.parse(text);
        for (Term term : result) {
            list.add(term.getName());
        }
        return list;
    }

    public static List<String> toAnalysisSeg(String text) {
        List<String> list = new ArrayList<>();
        Result result = ToAnalysis.parse(text);
        for (Term term : result) {
            list.add(term.getName());
        }
        return list;
    }

    public static List<String> nlpAnalysisSeg(String text) {
        List<String> list = new ArrayList<>();
        Result result = NlpAnalysis.parse(text);
        for (Term term : result) {
            list.add(term.getName());
        }
        return list;
    }

    public static List<String> indexAnalysisSeg(String text) {
        List<String> list = new ArrayList<>();
        Result result = IndexAnalysis.parse(text);
        for (Term term : result) {
            list.add(term.getName());
        }
        return list;
    }
}
