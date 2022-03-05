package com.fleet.alg;

import com.alibaba.fastjson.JSON;

public class AddDigits {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(addDigits1(911)));
    }

    public static int addDigits(int num) {
        int c = 0;
        if (num < 10) {
            return num;
        }
        while (num >= 10) {
            c = c + num % 10;
            num = num / 10;
        }
        c = c + num;

        if (c >= 10) {
            c = addDigits(c);
        }
        return c;
    }

    public static int addDigits1(int num) {
        if (num == 0) {
            return 0;
        }
        int r = num % 9;
        return r == 0 ? 9 : r;
    }
}
