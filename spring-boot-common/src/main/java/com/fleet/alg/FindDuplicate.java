package com.fleet.alg;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * <p>
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 */
public class FindDuplicate {

    public static void main(String[] args) {
        int[] nums = {3, 4, 1, 2, 5, 5};
        System.out.println(findDuplicate(nums));
    }

    public static int findDuplicate(int[] nums) {
        int length = nums.length;
        int left = 1, right = length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            int c = 0;
            for (int i = 0; i < length; i++) {
                if (nums[i] <= middle) {
                    c++;
                }
            }
            if (c <= middle) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return right;
    }
}
