package com.luni724.thread;

/**
 * 同步方法块
 * @author liq
 * @date 2020/5/25
 */
public class SynchronizedDemo {

    private static int i = 0;



    public static void main(String[] args) throws InterruptedException {

        Thread hightPriorityComplete = new Thread(() -> {
            int j = 0;
            while (j < 10000) {
                synchronized (SynchronizedDemo.class) {
                    i++;
                }
                j++;
            }
        });
        Thread lowerPriorityComplete = new Thread(() -> {
            int j = 0;
            while (j < 10000) {
                synchronized (SynchronizedDemo.class) {
                    i++;
                }
                j++;
            }
        });

        hightPriorityComplete.start();
        lowerPriorityComplete.start();
        hightPriorityComplete.join();
        lowerPriorityComplete.join();
        System.out.println("i:" + i);
        System.out.println(Thread.currentThread().getName() + " all done");

    }
}
