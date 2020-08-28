package com.fleet.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * 将非负整数转换为其对应的英文表示。可以保证给定输入小于 231 - 1 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: "One Hundred Twenty Three"
 * 示例 2:
 * <p>
 * 输入: 12345
 * 输出: "Twelve Thousand Three Hundred Forty Five"
 * 示例 3:
 * <p>
 * 输入: 1234567
 * 输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 示例 4:
 * <p>
 * 输入: 1234567891
 * 输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class NumberToWords {

    public static void main(String[] args) {
        System.out.println(numberToWords(1000000));
    }

    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        String r = "";

        int i = 0;
        String[] units = {"", "Thousand", "Million", "Billion"};
        while (num != 0) {
            List<String> list = new ArrayList<>();
            // 处理成三位数，加上单位
            int v = num % 1000;

            // 百位
            int h = v / 100;
            if (h > 0) {
                list.add(one(h) + " Hundred");
            }

            // 十位，各位
            int to = v % 100;
            if (to > 0 && to < 10) {
                list.add(one(to));
            } else if (to >= 10 && to < 20) {
                list.add(tenLessThan20(to));
            } else if (to >= 20) {
                int t = to / 10;
                String s = ten(t);
                int o = to % 10;
                if (o > 0) {
                    s += " " + one(o);
                }
                list.add(s);
            }
            if (list.size() != 0) {
                if (i > 0) {
                    if (r.isEmpty()) {
                        r = String.join(" ", list) + " " + units[i];
                    } else {
                        r = String.join(" ", list) + " " + units[i] + " " + r;
                    }
                } else {
                    r = String.join(" ", list);
                }
            }
            i++;
            num = num / 1000;
        }

        return r;
    }

    public static String one(int num) {
        switch (num) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
        }
        return "";
    }

    public static String tenLessThan20(int num) {
        switch (num) {
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
        }
        return "";
    }

    public static String ten(int num) {
        switch (num) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";
        }
        return "";
    }
}
