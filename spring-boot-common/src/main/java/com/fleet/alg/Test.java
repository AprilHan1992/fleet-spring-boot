package com.fleet.alg;

import java.lang.reflect.Field;

public class Test {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field myCache = cache.getDeclaredField("cache");
        myCache.setAccessible(true);

        Integer[] newCache = (Integer[]) myCache.get(cache);
        newCache[132] = newCache[133];

        int a = 1;
        int b = a + a;
        System.out.printf("%d + %d = %d", a, a, b);
    }
}
