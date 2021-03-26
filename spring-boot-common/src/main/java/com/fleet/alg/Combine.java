package com.fleet.alg;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Combine {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(combine(4, 3)));
    }

    public static List<List<Integer>> combine(int n, int k) {
        return combine(1, n, k);
    }

    public static List<List<Integer>> combine(int start, int n, int k) {
        List<List<Integer>> rList = new ArrayList<>();
        if (k == 1) {
            for (int i = start; i <= n - k + 1; i++) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                rList.add(list);
            }
            return rList;
        }
        for (int i = start; i <= n - k + 1; i++) {
            List<List<Integer>> r = combine(i + 1, n, k - 1);
            for (List<Integer> list : r) {
                list.add(i);
                rList.add(list);
            }
        }
        return rList;
    }
}
