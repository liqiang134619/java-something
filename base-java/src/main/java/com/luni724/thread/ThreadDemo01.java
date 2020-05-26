package com.luni724.thread;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * threadLocal
 * @author liq
 * @date 2020/5/25
 */

public class ThreadDemo01 {

    // 1. 保证线程安全解决方案， 加锁
//    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<>();

    public static class ParseDate implements Runnable {

        int i = 0;
        ParseDate(int i) {
            this.i = i;
        }
        @Override
        public void run() {

            try {

                if (tl.get() ==null) {
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date parse = tl.get().parse("2018-09-13 19:29:" + i % 60);
                System.out.println(i +":" + parse);
                tl.remove();

//                reentrantLock.lock();
//                sdf.parse("2018-09-13 19:29:" + i%60);
//                reentrantLock.unlock();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i < 100; i++) {
            executorService.execute(new ParseDate(i));
        }
        executorService.shutdown();
    }
}
