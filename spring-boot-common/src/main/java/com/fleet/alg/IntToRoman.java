package com.fleet.alg;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: "III"
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "IV"
 * 示例 3:
 * <p>
 * 输入: 9
 * 输出: "IX"
 * 示例 4:
 * <p>
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 * <p>
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class IntToRoman {

    public static void main(String[] args) {
        System.out.println(intToRoman(420));
    }

    public static String intToRoman(int num) {
        StringBuilder r = new StringBuilder();
        int k = num / 1000;
        for (int i = 0; i < k; i++) {
            r.append("M");
        }

        num = num % 1000;
        if (num >= 900) {
            r.append("CM");
        } else if (num >= 500) {
            r.append("D");
            int h = num - 500;
            if (h / 100 >= 1) {
                for (int i = 0; i < h / 100; i++) {
                    r.append("C");
                }
            }
        } else if (num >= 400) {
            r.append("CD");
        } else if (num >= 100) {
            for (int i = 0; i < num / 100; i++) {
                r.append("C");
            }
        }

        num = num % 100;
        if (num >= 90) {
            r.append("XC");
        } else if (num >= 50) {
            r.append("L");
            int h = num - 50;
            if (h / 10 >= 1) {
                for (int i = 0; i < h / 10; i++) {
                    r.append("X");
                }
            }
        } else if (num >= 40) {
            r.append("XL");
        } else if (num >= 10) {
            for (int i = 0; i < num / 10; i++) {
                r.append("X");
            }
        }

        num = num % 10;
        if (num >= 9) {
            r.append("IX");
        } else if (num >= 5) {
            r.append("V");
            int h = num - 5;
            if (h >= 1) {
                for (int i = 0; i < h; i++) {
                    r.append("I");
                }
            }
        } else if (num >= 4) {
            r.append("IV");
        } else if (num >= 1) {
            for (int i = 0; i < num; i++) {
                r.append("I");
            }
        }

        return r.toString();
    }
}
