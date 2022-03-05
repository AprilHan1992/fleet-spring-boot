package com.fleet.alg;

import java.util.ArrayList;
import java.util.List;

public class MedianFinder {

    static List<Integer> list = new ArrayList<>();

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {

    }

    public static void addNum(int num) {
//        int i = 0;
//        for (; i < list.size(); i++) {
//            if (list.get(i) > num) {
//                break;
//            }
//        }
//        list.add(i, num);

        int size = list.size();
        int left = 0, right = size - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (list.get(middle) >= num) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        list.add(left, num);
    }

    public static double findMedian() {
        int size = list.size();
        if (list.size() % 2 == 1) {
            return (double) list.get((size - 1) / 2);
        } else {
            return (double) (list.get(size / 2) + list.get(size / 2 - 1)) / 2;
        }
    }

    public static void main(String[] args) {
        addNum(1);
        addNum(2);
        System.out.println(findMedian());
        addNum(3);
        System.out.println(findMedian());
    }
}
