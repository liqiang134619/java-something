package com.luni724.thread;

import com.google.common.collect.Lists;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Liq
 * @date 2020/5/29
 */
public class ThreadCallableDemo {


    public static void main(String[] args) throws Exception {


        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<String>> callableList = Lists.newArrayList();


        long l = System.currentTimeMillis();
        Runnable runnable = ()->{
            System.out.println("xx");
        };

        Callable<String> callable5 = () -> {
            Thread.sleep(100L);
            return "a";
        };
        Callable<String> callable1 = () -> {
            Thread.sleep(300L);
            return "b";
        };
        Callable<String> callable2 = () -> {
            Thread.sleep(500L);
            return "c";
        };
        Callable<String> callable3 = () -> {
            Thread.sleep(300L);
            return "d";
        };
        Callable<String> callable4 = () -> {
            Thread.sleep(100L);
            return "e";
        };





        callableList.add(callable1);
        callableList.add(callable2);
        callableList.add(callable3);
        callableList.add(callable4);
        callableList.add(callable5);

        StringBuilder sb = new StringBuilder();
        List<Future<String>> futures = executorService.invokeAll(callableList);
        futures.forEach(f -> {

            try {
                sb.append(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        });

        executorService.execute(runnable);
        System.out.println("耗时：" + (System.currentTimeMillis() - l));
        System.out.println(sb.toString());
        System.out.println("==============================");

        long l1 = System.currentTimeMillis();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "a";
        },executorService);
        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "b";
        },executorService);
        CompletableFuture<String> stringCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "c";
        },executorService);
        CompletableFuture<String> stringCompletableFuture3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "d";
        },executorService);
        CompletableFuture<String> stringCompletableFuture4 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "e";
        },executorService);

        List<CompletableFuture<String>> futureList = Lists.newArrayList();

        futureList.add(stringCompletableFuture1);
        futureList.add(stringCompletableFuture2);
        futureList.add(stringCompletableFuture3);
        futureList.add(stringCompletableFuture4);
        futureList.add(stringCompletableFuture);

        StringBuilder sb2 =new StringBuilder();
        List<String> collect = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        collect.forEach(sb2::append);
        System.out.println(sb2.toString());
        System.out.println("耗时：" + (System.currentTimeMillis() - l1));
        executorService.shutdown();
    }
}
