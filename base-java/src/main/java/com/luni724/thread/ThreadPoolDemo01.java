package com.luni724.thread;

import java.util.concurrent.*;

/**
 * @author Liq
 * @date 2020/5/26
 */
public class ThreadPoolDemo01 {

    public static class  Task implements Runnable {

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static class DivTask implements Runnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        private int a;
        private  int b;

        DivTask(int a,int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            System.out.println(a / b);
        }
    }




    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 10; i++) {
//            executorService.execute(new Task());
//        }
//        executorService.shutdown();


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 200, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024)
                , new ThreadPoolExecutor.AbortPolicy()){

            // 自定义扩展线程池
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(new DivTask(100,i));
        }
        threadPoolExecutor.shutdown();

    }
}
