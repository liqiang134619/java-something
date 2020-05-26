package com.luni724.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 * @author Liq
 * @date 2020/5/26
 */
public class ReenterLock implements  Runnable {


    private static ReentrantLock reentrantLock = new ReentrantLock();
    public  static int i = 0;

    @Override
    public void run() {
        for (int i1 = 0; i1 < 100000; i1++) {
            reentrantLock.lock();
            i++;
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock reenterLock = new ReenterLock();
        Thread thread1 = new Thread(reenterLock);
        // ReentrantLock 要用static修饰 因为不是同一个lock锁
        Thread thread2 = new Thread(new ReenterLock());
        Thread thread3 = new Thread(reenterLock);
        Thread thread4 = new Thread(reenterLock);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        System.out.println(i);
    }
}
