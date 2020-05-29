package com.luni724.thread.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Liq
 * @date 2020/5/29
 */
public class DemoMain {

    public static void main(String[] args) {


        List<Shop> list = Arrays.asList(new Shop("one"),
                new Shop("two"),
                new Shop("three"),
                new Shop("four"));



        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 200, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024)
                , new ThreadPoolExecutor.AbortPolicy());

        ExecutorService executorService = Executors.newFixedThreadPool(5);


        // java8的基本并行流操作
        long l = System.currentTimeMillis();
        List<String> ip25s = list.stream().parallel()
                .map(shop -> String.format("%s price is %.2f " , shop.getName(), shop.getPrice("ip25s")))
                .collect(Collectors.toList());
        System.out.println("parallel耗时：" + (System.currentTimeMillis() - l));
        ip25s.forEach(System.out::println);

        System.out.println("=============================");


        //  completable单个异步操作
        long l1 = System.currentTimeMillis();
        List<CompletableFuture<String>> ip25s1 = list.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String
                .format("%s price is %.2f ", shop.getName(), shop.getPrice("ip25s")),threadPoolExecutor))
                .collect(Collectors.toList());
        List<String> collect = ip25s1.stream().map(CompletableFuture::join).collect(Collectors.toList());




        System.out.println("CompletableFuture（加了线程池）耗时：" + (System.currentTimeMillis() - l1));
        collect.forEach(System.out::println);

        threadPoolExecutor.shutdown();

    }
}
