package com.luni724.thread;

/**
 * wait - notify
 * @author Liq
 * @date 2020/5/25
 */
public class ThreadDemo02 {


    final static Object object = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            // wait notify 在 同步方法中
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + ": started");
                System.out.println(Thread.currentThread().getName() + ": wait");
                try {
                    //  释放锁
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": end");
            }

        });
        Thread thread1 = new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + ": started");
                try {
                    object.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": end");
            }

        });
        thread.start();
        thread1.start();
        System.out.println(Thread.currentThread().getName()+": start");

    }
}
