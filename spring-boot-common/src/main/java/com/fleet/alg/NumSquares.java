package com.fleet.alg;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * <p>
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
public class NumSquares {

    public static void main(String[] args) {
        System.out.println(numSquares(55));
    }

    public static int numSquares(int n) {
        if (n == 0) {
            return 0;
        }
        int c = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            c = Math.min(c, numSquares(n - i * i) + 1);
        }
        return c;
    }
}
