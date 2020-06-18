package com.luni724.thread.future;

import lombok.Data;

import java.text.Format;

/**
 * @author liq
 * @date 2020/5/29
 */
@Data
public class DisCount {
    public enum Code {

        NONE(0),SILVER(5),GOLD(10),PLATINUM(20),DIAMOND(25);
        private final int percentage;
        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote ){
        return quote.getShopName() + "price is" +
                DisCount.apply(quote.getPrice(),quote.getDisCountCode());
    }

    private static Double apply(Double price, Code disCountCode) {
        delay();
        return price * (100 - disCountCode.percentage) / 100;

    }

    private static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
