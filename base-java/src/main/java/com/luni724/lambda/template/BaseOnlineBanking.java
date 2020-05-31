package com.luni724.lambda.template;

import com.sun.tools.classfile.CharacterRangeTable_attribute;

import java.util.Random;
import java.util.function.Consumer;

/**
 * 通用的方法模板
 * @author liq
 * @date 2020/5/31
 */
public abstract  class BaseOnlineBanking {

    public void processCustomer() {
        Random random = new Random();
        // 随机生成一个
        int i = random.nextInt();
        Customer customer = new Customer(i);
        makeCustomerHappy(customer);

    }

    // 添加一个重载方法
    public void processCustomer(Consumer<Customer> makeCustomerHappy) {
        Random random = new Random();
        // 随机生成一个
        int i = random.nextInt();
        Customer customer = new Customer(i);
        makeCustomerHappy.accept(customer);

    }


    abstract void makeCustomerHappy(Customer c);
}
