package com.luni724.lambda.template;

import sun.tools.attach.HotSpotVirtualMachine;

import java.util.function.Consumer;

/**
 * @author liq
 * @date 2020/5/31
 */
public class TemplateMain {


    public static void main(String[] args) {

        NormalOnlineBanking normalOnlineBanking = new NormalOnlineBanking();
        normalOnlineBanking.processCustomer();


        new LamdaOnlineBanking().processCustomer(customer -> System.out.println(customer.getName() + " labmda happy"));
    }

}
