package com.luni724.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  CAS(compare and swap) 比较交换
 * @author Liq
 * @date 2020/5/26
 */
public class AtomicIntegerDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger();
//    private static int atomicInteger = 0;

    public static class addThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                atomicInteger.incrementAndGet();
//                atomicInteger++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new addThread());
        Thread thread2 = new Thread(new addThread());
        Thread thread3 = new Thread(new addThread());
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(atomicInteger);

    }

}
