package com.fleet.alg;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例：
 * <p>
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(generateParenthesis(4)));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> rList = new ArrayList<>();
        build(0, new char[2 * n], rList);
        return rList;
    }

    public static void build(int position, char[] chars, List<String> rList) {
        if (chars.length == position) {
            if (valid(chars)) {
                rList.add(new String(chars));
            }
        } else {
            chars[position] = '(';
            build(position + 1, chars, rList);
            chars[position] = ')';
            build(position + 1, chars, rList);
        }
    }

    public static boolean valid(char[] chars) {
        int co = 0;
        for (char c : chars) {
            if (c == '(') {
                co++;
            } else {
                co--;
            }
            if (co < 0) {
                return false;
            }
        }
        return co == 0;
    }
}
