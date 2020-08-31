package com.fleet.alg;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class LetterCombinations {

    public static void main(String[] args) {
        List<String> list = letterCombinations("123");
        System.out.println(JSON.toJSONString(list));
    }

    public static List<String> letterCombinations(String digits) {
        String[][] ss = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}, {"j", "k", "l"}, {"m", "n", "o"}, {"p", "q", "r", "s"}, {"t", "u", "v"}, {"w", "x", "y", "z"}};
        List<String> rlist = new ArrayList<>();
        for (int i = digits.length() - 1; i >= 0; i--) {
            if (digits.charAt(i) == '1' || digits.charAt(i) == '0') {
                continue;
            }
            if (rlist.size() == 0) {
                int n = digits.charAt(i) - '0';
                rlist.addAll(Arrays.asList(ss[n - 2]));
            } else {
                List<String> list = new ArrayList<>();
                for (String string : rlist) {
                    int n = digits.charAt(i) - '0';
                    for (String s : ss[n - 2]) {
                        list.add(s + string);
                    }
                }
                rlist = list;
            }
        }
        return rlist;
    }
}
