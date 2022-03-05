package com.fleet.alg;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(" "));
    }

    public static int lengthOfLongestSubstring(String s) {
        int c = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            Map<Character, Integer> map = new HashMap<>();
            for (int j = i; j < length; j++) {
                if (map.containsKey(s.charAt(j))) {
                    break;
                } else {
                    map.put(s.charAt(j), j);
                }
            }
            c = Math.max(c, map.size());
        }
        return c;
    }
}
