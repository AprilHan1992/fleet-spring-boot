package com.fleet.alg;

/**
 * 编写一个程序，找出第 n 个丑数。
 * <p>
 * 丑数就是质因数只包含 2, 3, 5 的正整数。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 * <p>
 * 1 是丑数。
 * n 不超过1690。
 */
public class NthUglyNumber {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }

    public static int nthUglyNumber(int n) {
        int[] nums = new int[n];
        nums[0] = 1;
        int x = 0, y = 0, z = 0;
        for (int i = 1; i < n; i++) {
            nums[i] = Math.min(Math.min(nums[x] * 2, nums[y] * 3), nums[z] * 5);
            if (nums[i] == nums[x] * 2) {
                x++;
            }
            if (nums[i] == nums[y] * 3) {
                y++;
            }
            if (nums[i] == nums[z] * 5) {
                z++;
            }
        }
        return nums[n - 1];
    }
}
