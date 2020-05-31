package com.luni724.lambda.strategy;

/**
 * 策略模式实现接口
 * @author liq
 * @date 2020/5/31
 */
public interface ValidationStrategy {

    boolean execute(String s);
}
