package com.fleet.alg;

/**
 * 排序
 *
 * @author April Han
 */
public class Sort {

    public static void main(String[] args) {
        int[] array = {3, 2, 7, 4, 9, 3, 3, 8, 1, 6};
//        System.out.println(JSON.toJSONString(bubble(array)));
//        System.out.println(JSON.toJSONString(insertion(array)));
//        System.out.println(JSON.toJSONString(shell(array)));
//        System.out.println(JSON.toJSONString(quick(array, 0, array.length - 1)));
//        System.out.println(JSON.toJSONString(selection(array)));
//        System.out.println(JSON.toJSONString(merge(array, 0, array.length - 1)));
//        System.out.println(JSON.toJSONString(count(array)));
    }

    /**
     * 冒泡排序
     */
    public static int[] bubble(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 插入排序
     */
    public static int[] insertion(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j - 1] > array[j]; j--) {
                int temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
        return array;
    }

    /**
     * 希尔排序
     */
    public static int[] shell(int[] array) {
        int step = array.length / 2;
        while (step >= 1) {
            for (int i = 0; i < step; i++) {
                for (int j = i + step; j < array.length; j += step) {
                    for (int k = j; k >= step && array[k - step] > array[k]; k -= step) {
                        int temp = array[k];
                        array[k] = array[k - step];
                        array[k - step] = temp;
                    }
                }
            }
            step = step / 2;
        }
        return array;
    }

    /**
     * 快速排序
     */
    public static int[] quick(int[] array, int low, int high) {
        int i = low;
        int j = high;
        if (i < j) {
            int base = array[i];
            while (i < j) {
                while (i < j && array[j] > base) {
                    j--;
                }
                array[i] = array[j];
                while (i < j && array[i] <= base) {
                    i++;
                }
                array[j] = array[i];
            }
            array[i] = base;
        }
        if (i - 1 > low) {
            array = quick(array, low, i - 1);
        }
        if (j + 1 < high) {
            array = quick(array, j + 1, high);
        }
        return array;
    }

    /**
     * 选择排序
     */
    public static int[] selection(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
        return array;
    }

    /**
     * 归并排序
     */
    public static int[] merge(int[] array, int low, int high) {
        if (low == high) {
            return new int[]{array[low]};
        }

        int mid = (low + high) / 2;
        // 左有序数组
        int[] leftArray = merge(array, low, mid);
        // 右有序数组
        int[] rightArray = merge(array, mid + 1, high);
        // 新有序数组
        int[] newArray = new int[leftArray.length + rightArray.length];

        int m = 0, i = 0, j = 0;
        while (i < leftArray.length && j < rightArray.length) {
            newArray[m++] = leftArray[i] < rightArray[j] ? leftArray[i++] : rightArray[j++];
        }
        while (i < leftArray.length) {
            newArray[m++] = leftArray[i++];
        }
        while (j < rightArray.length) {
            newArray[m++] = rightArray[j++];
        }
        return newArray;
    }

    /**
     * 计数排序
     */
    public static int[] count(int[] array) {
        // 获取数列的最大值
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        // 根据最大值确定统计数组的长度
        int len = max - min + 1;
        int[] c = new int[len];
        // 遍历数列，填充统计数组
        for (int i = 0; i < array.length; i++) {
            c[array[i] - min]++;
        }

        // 遍历统计数组，输出结果
        int index = 0;
        int[] r = new int[array.length];
        for (int i = 0; i < c.length; i++) {
            while (c[i] > 0) {
                r[index++] = i + min;
                c[i]--;
            }
        }
        return r;
    }

    /**
     * 计数排序
     */
    public static int[] count1(int[] array) {
        // 获取数列的最大值
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        // 根据最大值确定统计数组的长度
        int len = max - min + 1;
        int[] c = new int[len];
        // 遍历数列，填充统计数组，数组 c[] 中的每一个值表示它所对应的下标在排序后数组的出现次数
        for (int i = 0; i < array.length; i++) {
            c[array[i] - min]++;
        }

        // 新的 c[] 将表示他们的位置信息
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i] + c[i - 1];
        }

        // 遍历 array[] 中的元素，填充到结果数组中，输出结果
        int[] r = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            r[c[array[i] - min] - 1] = array[i];
            c[array[i] - min]--;
        }
        return r;
    }
}
