package com.fleet.alg;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cabbac"));
    }

    public static String longestPalindrome(String s) {
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        String r = "";
        for (int i = 0; i < length - 1; i++) {
            String s1 = getPlalindrome(s, i);
            if (r.length() < s1.length()) {
                r = s1;
            }
        }
        return r;
    }

    public static String getPlalindrome(String s, int i) {
        String s1 = "", s2 = "";
        if (s.charAt(i) == s.charAt(i + 1)) {
            int left = i, right = i + 1;
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                } else {
                    s1 = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
        }

        int left = i, right = i;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            } else {
                s2 = s.substring(left, right + 1);
            }
            left--;
            right++;
        }
        if (s1.length() > s2.length()) {
            return s1;
        }
        return s2;
    }
}
