package com.example.shopping.enums;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum FuritEnum {
    STRAWBERRY("草莓", "STRAWBERRY", new BigDecimal("13")),
    APPLE("苹果", "APPLE", new BigDecimal("8.00")),
    MANGO("芒果", "MANGO", new BigDecimal("20.00"));

    FuritEnum(String name, String code, BigDecimal price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    private String name;
    private String code;
    private BigDecimal price;
}
