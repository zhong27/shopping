package com.example.shopping.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Furit {
    //名称
    String name;

    //水果编码
    public String code;

    //单价，单位元
    BigDecimal price;

    //总价，单位元
    BigDecimal totalPrice;

    //折扣，大于0且小于1，实例：0.8代表8折
    BigDecimal discount;

    //斤数，单位斤
    BigDecimal number;

    //优惠类型，1：不享受优惠，2：打折，3：满减，4：打折和满减
    String discountType;

}
