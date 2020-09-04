package com.fleet.alg;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 */
public class IsNumber {

    public static void main(String[] args) {
        System.out.println(isNumber("+100"));
//        System.out.println(isNumber("5e2"));
//        System.out.println(isNumber("-123"));
//        System.out.println(isNumber("3.1416"));
//        System.out.println(isNumber("-1E-16"));
//        System.out.println(isNumber("0123"));
//        System.out.println(isNumber("12e"));
//        System.out.println(isNumber("1a3.14"));
//        System.out.println(isNumber("1.2.3"));
//        System.out.println(isNumber("+-5"));
//        System.out.println(isNumber("12e+5.4"));
    }

    public static boolean isNumber(String s) {
        // 小数点位
        boolean point = false;
        // 数字位
        boolean digit = false;
        // e位
        boolean e = false;
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                digit = true;
            } else if (c == '+' || c == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (c == '.') {
                if (e || point) {
                    return false;
                }
                point = true;
            } else if (c == 'e' || c == 'E') {
                if (e || !digit) {
                    return false;
                }
                e = true;
                // 防止e后面没有数字
                digit = false;
            } else {
                return false;
            }
        }
        return digit;
    }
}
