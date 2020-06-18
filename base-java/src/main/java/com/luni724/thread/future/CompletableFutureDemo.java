package com.luni724.thread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Liq
 * @date 2020/6/9
 */
public class CompletableFutureDemo {


    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {


        CompletableFuture<String> future = CompletableFuture.supplyAsync((() -> {
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }));

        String s = future.get(5000L,TimeUnit.MILLISECONDS);

        System.out.println(s);
    }
}
