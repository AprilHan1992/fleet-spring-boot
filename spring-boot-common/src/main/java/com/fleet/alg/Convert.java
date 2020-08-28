package com.fleet.alg;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L   D   R
 * E O E I I
 * E C I H N
 * T   S   G
 */
public class Convert {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 4));
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int step = 2 * numRows - 2;
            if (i == 0 || i == numRows - 1) {
                int j = i;
                while (j < s.length()) {
                    r.append(s.charAt(j));
                    j = j + step;
                }
            } else {
                int j = i;
                int step1 = step - 2 * i;
                int step2 = 2 * i;
                // 判断步长
                int k = 0;
                while (j < s.length()) {
                    r.append(s.charAt(j));
                    if (k % 2 == 0) {
                        j = j + step1;
                    } else {
                        j = j + step2;
                    }
                    k++;
                }
            }
        }
        return r.toString();
    }
}
