package com.fleet.alg;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int length = length1 + length2;
        int left1 = 0;
        int left2 = 0;
        if ((length) % 2 == 1) {
            int i = (length - 1) / 2;
            int j = 0;
            int v = 0;
            while (!(left1 >= length1 && left2 >= length2)) {
                int temp;
                if (left1 < length1 && left2 < length2) {
                    if (nums1[left1] <= nums2[left2]) {
                        temp = nums1[left1];
                        left1++;
                    } else {
                        temp = nums2[left2];
                        left2++;
                    }
                } else {
                    if (left1 >= length1) {
                        temp = nums2[left2];
                        left2++;
                    } else {
                        temp = nums1[left1];
                        left1++;
                    }
                }
                if (j == i) {
                    v = temp;
                    break;
                }
                j++;
            }
            return (double) v;
        } else {
            int i = length / 2;
            int j = length / 2 - 1;
            int k = 0;
            int v1 = 0;
            int v2 = 0;
            while (!(left1 >= length1 && left2 >= length2)) {
                int temp;
                if (left1 < length1 && left2 < length2) {
                    if (nums1[left1] <= nums2[left2]) {
                        temp = nums1[left1];
                        left1++;
                    } else {
                        temp = nums2[left2];
                        left2++;
                    }
                } else {
                    if (left1 >= length1) {
                        temp = nums2[left2];
                        left2++;
                    } else {
                        temp = nums1[left1];
                        left1++;
                    }
                }
                if (k == i) {
                    v1 = temp;
                    break;
                }
                if (k == j) {
                    v2 = temp;
                }
                k++;
            }
            return (double) (v1 + v2) / 2;
        }
    }
}
