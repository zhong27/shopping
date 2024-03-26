package com.example.shopping.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum ReduceRulesEnum {
    RULE_100_10("001", new BigDecimal("100.00"), new BigDecimal("10.00"));

    ReduceRulesEnum(String code, BigDecimal fullAmount, BigDecimal reduceAmount) {
        this.code = code;
        this.fullAmount = fullAmount;
        this.reduceAmount = reduceAmount;
    }

    //编码
    private String code;

    //满足金额
    private BigDecimal fullAmount;

    //减少金额
    private BigDecimal reduceAmount;


    /**
     * 根据code查找
     *
     * @param code 枚举code
     * @return 枚举对象
     */
    public static ReduceRulesEnum findEnumByCode(String code) {
        for (ReduceRulesEnum ruleEnum : ReduceRulesEnum.values()) {
            if (ruleEnum.getCode().equals(code)) {
                //如果需要直接返回name则更改返回类型为String,return statusEnum.name;
                return ruleEnum;
            }
        }
        throw new IllegalArgumentException("code is invalid");
    }

}
