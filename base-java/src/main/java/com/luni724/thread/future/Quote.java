package com.luni724.thread.future;

import com.luni724.thread.PriorityDemo;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liq
 * @date 2020/5/29
 */
@Data
@AllArgsConstructor
public class Quote {

    private final String shopName;

    private final Double price;

    private final DisCount.Code disCountCode;


    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double v = Double.parseDouble(split[1]);
        DisCount.Code code = DisCount.Code.valueOf(split[2]);
        return new Quote(shopName, v,code);
    }




}
