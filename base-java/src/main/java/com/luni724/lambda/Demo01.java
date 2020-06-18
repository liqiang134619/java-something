package com.luni724.lambda;

/**
 * @author liq
 * @date 2020/5/31
 */
public class Demo01 {

    public static void main(String[] args) {


        // 简单匿名内部类
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("xx");
            }
        };



        // lambda
        Runnable runnable1 = ()-> System.out.println("xx");


    }
}
