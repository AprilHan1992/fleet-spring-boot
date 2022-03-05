package com.fleet.alg;

import java.util.Arrays;

/**
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 */
public class MissingNumber {

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 0, 5};
        System.out.println(missingNumber(nums));
    }

    public static int missingNumber(int[] nums) {
        Arrays.sort(nums);
        if (nums[0] != 0) {
            return 0;
        }
        int length = nums.length;
        if (length != nums[length - 1]) {
            return length;
        }
        for (int i = 1; i < length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return -1;
    }
}
