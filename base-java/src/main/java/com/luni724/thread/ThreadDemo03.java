package com.luni724.thread;

import java.util.concurrent.CompletableFuture;

/**join 等待线程结束
 * @author Liq
 * @date 2020/5/25
 */
public class ThreadDemo03 {

    private static volatile int i = 0;
    public static void main(String[] args) throws InterruptedException {

//        Thread thread = new Thread(() -> {
//            for (i = 0; i < 10000; i++) {
//            }
//        });
//        thread.start();
//        thread.join();
//        System.out.println(Thread.currentThread().getName() + ":"+ i);
        String join = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(120L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "a";
        }).join();

        System.out.println(Thread.currentThread().getName());

        System.out.println(join);


    }
}
