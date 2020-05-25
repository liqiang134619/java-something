package com.luni724.thread;

/**
 * @author liq
 * @date 2020/5/25
 */
public class PriorityDemo {

    private static int i = 0;

    private static int j = 0;


    public static void main(String[] args) throws InterruptedException {

        Thread hightPriorityComplete = new Thread(() -> {
            while (true) {
                synchronized (PriorityDemo.class) {
                    i++;
                    if (i > 10000) {
                        System.out.println("hightPriority complete");
                        break;
                    }

                }
            }
        });
        Thread lowerPriorityComplete = new Thread(() -> {
            while (true) {
                synchronized (PriorityDemo.class) {
                    j++;
                    if (j > 10000) {
                        System.out.println("lowerPriority complete");
                        break;
                    }

                }
            }
        });

        hightPriorityComplete.setPriority(10);
        lowerPriorityComplete.setPriority(5);
        hightPriorityComplete.start();
        lowerPriorityComplete.start();
//        hightPriorityComplete.join();
//        lowerPriorityComplete.join();
        System.out.println("i:" + i);
        System.out.println("j:" + j);
        System.out.println(Thread.currentThread().getName() + " all done");

    }
}
