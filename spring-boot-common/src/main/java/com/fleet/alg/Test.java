package com.fleet.alg;

public class Test {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        Class cache = Integer.class.getDeclaredClasses()[0];
//        Field myCache = cache.getDeclaredField("cache");
//        myCache.setAccessible(true);
//
//        Integer[] newCache = (Integer[]) myCache.get(cache);
//        newCache[132] = newCache[133];
//
//        int a = 1;
//        int b = a + a;
//        System.out.printf("%d + %d = %d", a, a, b);

        Integer i = 5;
        multiply(i);
        System.out.println(i);
    }

    public static void multiply(Integer i) {
////        i.intValue(i * i);
        i++;
        System.out.println(i);
    }

    public int removeElement(int[] nums, int val) {
        int c = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                c++;
            }
        }
        return c;
    }
}
