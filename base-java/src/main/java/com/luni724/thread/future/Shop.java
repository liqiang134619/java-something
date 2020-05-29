package com.luni724.thread.future;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Liq
 * @date 2019/8/2
 */
@Data
@AllArgsConstructor
public class Shop {


    private String name;




//    ExecutorService executorService = new ThreadPoolExecutor(1,1,0L,
//        TimeUnit.MILLISECONDS,
//        new LinkedBlockingDeque<>(1024));

    /**
     * java8之后 CompletableFuture
     * @param product
     * @return
     */
//    public Future<Double> getPriceAsync(String product) {
//
//        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//        executorService.execute(() -> {
//            System.out.println(Thread.currentThread().getName());
//            double price = calculatePrice(product);
//            futurePrice.complete(price);
//        });
//
//        return futurePrice;
//    }

    /**
     * java8之后 CompletableFuture
     * @param product
     * @return
     */
//    public Future<Double> getPriceAsync1(String product) {
//
//       return CompletableFuture.supplyAsync(()->calculatePrice(product),executorService);
//    }


    /**
     * java8 之前异步调用
     * @param product
     * @return
     */
//    public Future<Double> getPriceAsnc2(String product) {
//        return executorService.submit(()->calculatePrice(product));
//
//    }

    /**
     * 同步调用
     * @param product
     * @return
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        Random random = new Random();
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private static void delay() {
        try {
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
