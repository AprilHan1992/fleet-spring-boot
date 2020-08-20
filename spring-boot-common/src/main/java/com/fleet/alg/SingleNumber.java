package com.fleet.alg;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};
        System.out.println(JSON.toJSONString(singleNumber(nums)));
    }

    public static int[] singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int[] r = new int[2];
        int i = 0;
        for (int n : map.keySet()) {
            if (map.get(n) == 1) {
                r[i++] = n;
            }
            if (i >= 2) {
                break;
            }
        }
        return r;
    }
}
