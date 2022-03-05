package com.fleet.alg;

import java.util.concurrent.CountDownLatch;

/**
 * @author April Han
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch cdl0 = new CountDownLatch(0);
        CountDownLatch cdl1 = new CountDownLatch(1);
        CountDownLatch cdl2 = new CountDownLatch(1);
        Thread t1 = new Thread(new Count(cdl0, cdl1));
        Thread t2 = new Thread(new Count(cdl1, cdl2));
        Thread t3 = new Thread(new Count(cdl2, cdl2));
        t3.start();
        t2.start();
        t1.start();
    }

    static class Count implements Runnable {

        CountDownLatch cdl1;

        CountDownLatch cdl2;

        /**
         * 该构造器传递了上一个线程的计数器和当前线程的计数器
         */
        public Count(CountDownLatch cdl1, CountDownLatch cdl2) {
            super();
            this.cdl1 = cdl1;
            this.cdl2 = cdl2;
        }

        @Override
        public void run() {
            try {
                cdl1.await();
                System.out.println("线程：" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                cdl2.countDown();
            }
        }
    }
}
