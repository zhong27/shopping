package com.example.shopping.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum DiscountTypeEnum {
    NONE("不享受优惠", "1"),
    DISCOUNT("打折", "2"),
    FULL_REDUCTION("满减", "3"),
    DISCOUNT_FULL("打折和满减", "4");

    DiscountTypeEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    private String name;
    private String code;
}
