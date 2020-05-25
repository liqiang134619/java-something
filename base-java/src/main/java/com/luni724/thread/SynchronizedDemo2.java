package com.luni724.thread;

/**
 * 同步方法
 * @author liq
 * @date 2020/5/25
 */
public class SynchronizedDemo2 implements Runnable {

    static SynchronizedDemo2 instance = new SynchronizedDemo2();

    private static int i = 0;


    @Override
    public void run() {
        for (int i1 = 0; i1 < 10000; i1++) {
            increase();
        }
    }

    private synchronized void increase() {
        i++;
    }

    // 静态同步方法 -- 此时的加锁对象是类
//    private static synchronized void increase() {
//        i++;
//    }


    public static void main(String[] args) throws InterruptedException {
        // 因为加锁对象不是一个，计算结果会出错  修改同步方法为static 此时的加锁对象不再是对象实例，而是类
//        Thread thread = new Thread(new SynchronizedDemo2());
//        Thread thread1 = new Thread(new SynchronizedDemo2());


        // 使用同一个实例，成功
        Thread thread = new Thread(instance);
        Thread thread1 = new Thread(instance);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(i);
    }
}
