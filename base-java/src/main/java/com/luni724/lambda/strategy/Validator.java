package com.luni724.lambda.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liq
 * @date 2020/5/31
 */
@Data
@AllArgsConstructor
public class Validator {

    private final ValidationStrategy strategy;



    private boolean validate(String s) {
        return strategy.execute(s);
    }


    public static void main(String[] args) {

        // 两种策略实现
        Validator validator = new Validator(new IsNumberic());
        boolean validate = validator.validate("123");
        System.out.println(validate);

        Validator validator1 = new Validator(new IsAllLowerCase());
        boolean validate1 = validator1.validate("aaa");
        System.out.println(validate1);

        Validator lowercaseValidator = new Validator(s -> s.matches("\\d+"));
        Validator numbericValidator = new Validator(s->s.matches("[a-z]+"));
        boolean validate2 = lowercaseValidator.validate("123");
        boolean validate3 = numbericValidator.validate("123");

    }



}
