package com.luni724.thread.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @author liq
 * @date 2020/5/31
 */
public class Demo03Main {

    public static void main(String[] args) {

        List<Shop> list = Arrays.asList(new Shop("one"),
                new Shop("two"),
                new Shop("three"),
                new Shop("four"),
                new Shop("five"));

        ExecutorService executorService = Executors.newFixedThreadPool(8);


        long l = System.currentTimeMillis();


        // completion事件

        Stream<CompletableFuture<String>> ip57s2 = list.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice2("ip57s"), executorService))
                .map(future -> future.thenApply(Quote::parse))
                .map(futrue -> futrue.thenCompose(quote -> CompletableFuture.supplyAsync(() -> DisCount.applyDiscount(quote), executorService)));

        // thenAccept 注册一个监听事件，当future事件完成时候或计算结果可用时执行某个操作
        Stream<CompletableFuture<Void>> completableFutureStream = ip57s2.map(f -> f.thenAccept(fu -> System.out.println(fu + " 耗时 " + (System.currentTimeMillis() - l) + "毫秒")));

        CompletableFuture[] completableFutures = completableFutureStream.toArray(CompletableFuture[]::new);
        // allOf,anyOf 决定程序什么时候结束： 操作全部完成，完成部分操作
        CompletableFuture.allOf(completableFutures).join();


        executorService.shutdown();

    }
}
