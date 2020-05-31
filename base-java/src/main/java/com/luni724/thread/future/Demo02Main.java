package com.luni724.thread.future;

import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liq
 * @date 2020/5/29
 */
public class Demo02Main {

    public static void main(String[] args) {
        List<Shop> list = Arrays.asList(new Shop("one"),
                new Shop("two"),
                new Shop("three"),
                new Shop("four"),
                new Shop("five"));

        Shop shopTest= new Shop("test");

        ExecutorService executorService = Executors.newFixedThreadPool(8);


        long l = System.currentTimeMillis();
        // 1. 多级map操作
        List<String> ip57s = list.stream().map(shop -> shop.getPrice2("ip57s"))
                .map(Quote::parse)
                .map(DisCount::applyDiscount)
                .collect(Collectors.toList());
        System.out.println(ip57s);
        System.out.println("耗时：" +(System.currentTimeMillis() - l));


        System.out.println("======================");

        // 2.completableFuture
        long l1 = System.currentTimeMillis();
        List<CompletableFuture<String>> ip57s1 = list.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice2("ip57s"), executorService))
                .map(future -> future.thenApply(Quote::parse))
                .map(futrue -> futrue.thenCompose(quote -> CompletableFuture.supplyAsync(() -> DisCount.applyDiscount(quote), executorService)))
                .collect(Collectors.toList());

        List<String> collect = ip57s1.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("耗时：" +(System.currentTimeMillis() - l1));

        System.out.println("----------------");


        long l2 = System.currentTimeMillis();
        // 单个值进行combine
//        CompletableFuture.sup/plyAsync(()->Shop)
        CompletableFuture<Double> ip80s = CompletableFuture.supplyAsync(() -> shopTest.getPrice("ip80s"))
                .thenCombine(CompletableFuture.supplyAsync(() -> 100D), (v1, v2) -> v1 * v2);
        Double join = ip80s.join();
        System.out.println(join);
        System.out.println("耗时：" +(System.currentTimeMillis() - l2));
        executorService.shutdown();
    }
}
