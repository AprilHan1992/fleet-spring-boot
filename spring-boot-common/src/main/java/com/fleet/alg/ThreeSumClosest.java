package com.fleet.alg;

import java.util.Arrays;

public class ThreeSumClosest {

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4, 8, 20, 9, 60, 7, 7, 20};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        int max = nums[length - 1] + nums[length - 2] + nums[length - 3];
        if (target >= max) {
            return max;
        }
        int min = nums[0] + nums[1] + nums[2];
        if (min >= target) {
            return min;
        }
        int sum = min;
        for (int i = 0; i < length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int s = nums[i] + nums[left] + nums[right];
                if (Math.abs(s - target) < Math.abs(sum - target)) {
                    sum = s;
                }
                if (s == target) {
                    return s;
                }
                if (s > target) {
                    right--;
                }
                if (s < target) {
                    left++;
                }
            }
            if (nums[i] > target) {
                break;
            }
        }
        return sum;
    }
}
