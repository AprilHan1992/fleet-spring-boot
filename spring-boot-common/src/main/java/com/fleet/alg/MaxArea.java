package com.fleet.alg;

/**
 * 给出一个非负整数数组 a1，a2，a3，…… an，每个整数标识一个竖立在坐标轴 x 位置的一堵高度为 ai 的墙，选择两堵墙，和 x 轴构成的容器可以容纳最多的水。
 *
 * @author April Han
 */
public class MaxArea {

    public static void main(String[] args) {
        int[] hights = {7, 20, 1, 6};
        System.out.println(maxArea(hights));
    }

    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }

    public static int maxArea1(int[] array) {
        int max = 0, start = 0, end = array.length - 1;
        while (start < end) {
            int width = end - start;
            int height = 0;
            if (array[start] < array[end]) {
                height = array[start];
                start++;
            } else {
                height = array[end];
                end--;
            }
            int temp = width * height;
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }
}
