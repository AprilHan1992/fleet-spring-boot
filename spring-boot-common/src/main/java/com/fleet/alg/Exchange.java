package com.fleet.alg;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * 示例：
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 */
public class Exchange {

    public static void main(String[] args) {
        int[] nums = {1, 8, 3};
        System.out.println(exchange1(nums));
    }

    public static int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int left = 0;
        int right = nums.length - 1;
        int[] r = new int[nums.length];
        for (int num : nums) {
            if ((num & 1) == 0) {
                // 偶数
                r[right--] = num;
            } else {
                // 奇数
                r[left++] = num;
            }
        }
        return r;
    }


    public static int[] exchange1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 奇数
            while (left < right && (nums[left] & 1) == 1) {
                left++;
            }
            // 偶数
            while (left < right && (nums[right] & 1) == 0) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return nums;
    }
}
