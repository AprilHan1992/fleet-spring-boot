package com.fleet.alg;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix1(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int length = strs[0].length();
        while (length > 0) {
            boolean start = true;
            String r = strs[0].substring(0, length);
            for (int i = 1; i < strs.length; i++) {
                if (!strs[i].startsWith(r)) {
                    start = false;
                }
            }
            if (start) {
                return r;
            }
            length--;
        }
        return "";
    }

    public static String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String r = strs[0];
        for (int i = 1; i < strs.length; i++) {
            r = longestCommonPrefix(r, strs[i]);
            if (r.length() == 0) {
                break;
            }
        }
        return r;
    }

    public static String longestCommonPrefix(String s1, String s2) {
        int length = Math.min(s1.length(), s2.length());
        int index = 0;
        while (index < length) {
            if (s1.charAt(index) != s2.charAt(index)) {
                break;
            }
            index++;
        }
        return s1.substring(0, index);
    }
}
