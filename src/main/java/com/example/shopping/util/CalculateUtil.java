package com.example.shopping.util;

import com.example.shopping.entity.Furit;
import com.example.shopping.enums.DiscountTypeEnum;
import com.example.shopping.enums.ReduceRulesEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalculateUtil {

    private static List<String> DISCOUNT_LIST = Arrays.asList(DiscountTypeEnum.DISCOUNT.getCode(), DiscountTypeEnum.DISCOUNT_FULL.getCode());

    private static List<String> FULL_REDUCE_LIST = Arrays.asList(DiscountTypeEnum.FULL_REDUCTION.getCode(), DiscountTypeEnum.DISCOUNT_FULL.getCode());

    private static List<String> NOT_FULL_REDUCE = Arrays.asList(DiscountTypeEnum.NONE.getCode(), DiscountTypeEnum.DISCOUNT.getCode());


    public static void getTotalPrice(Furit furit) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        furit.setTotalPrice(totalPrice);
        if (furit == null) {
            return;
        }
        if (furit.getNumber() == null
                || furit.getPrice() == null
                || furit.getDiscount() == null) {
            return;
        }

        //数量处理
        BigDecimal number = verifyNumber(furit.getNumber());
        totalPrice = number.multiply(furit.getPrice());
        if (DISCOUNT_LIST.contains(furit.getDiscountType())) {
            //折扣处理
            BigDecimal disCount = verifyDisCount(furit.getDiscount());
            totalPrice = totalPrice.multiply(disCount);
        }
        totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        furit.setTotalPrice(totalPrice);
    }

    public static BigDecimal verifyDisCount(BigDecimal discount) {
        if (discount == null) {
            return BigDecimal.ONE;
        }
        if (discount.compareTo(BigDecimal.ZERO) == -1) {
            return BigDecimal.ONE;
        }
        if (discount.compareTo(BigDecimal.ONE) == 1) {
            return BigDecimal.ONE;
        }
        return discount;
    }

    public static BigDecimal verifyNumber(BigDecimal number) {
        if (number == null) {
            return BigDecimal.ZERO;
        }
        if (number.compareTo(BigDecimal.ZERO) == -1) {
            return BigDecimal.ZERO;
        }

        return number;
    }

    /*
     * 计算满减金额,单次
     * */
    public static BigDecimal getFullReduce(List<Furit> furitList, String ruleCode) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal retNum = BigDecimal.ZERO;

        List<Furit> filterList = furitList.stream()
                .filter(item -> FULL_REDUCE_LIST.contains(item.getDiscountType()))
                .collect(Collectors.toList());

        ReduceRulesEnum rulesEnum = ReduceRulesEnum.findEnumByCode(ruleCode);
        for (Furit furit : filterList) {
            getTotalPrice(furit);
            totalPrice = totalPrice.add(furit.getTotalPrice());
        }

        if (totalPrice.compareTo(new BigDecimal("100")) == -1) {
            retNum = totalPrice;
        }
        BigDecimal divide = totalPrice.divide(rulesEnum.getFullAmount(), 0, BigDecimal.ROUND_DOWN);
        if (divide.compareTo(BigDecimal.ZERO) == 1) {
            retNum = totalPrice.subtract(rulesEnum.getReduceAmount());
        }
        System.out.println("满减前金额: " + totalPrice + "，满减后金额: " + retNum);

        return retNum;
    }

    /*
     * 计算满减金额,多次
     * */
    public static BigDecimal getManyFullReduce(List<Furit> furitList, String ruleCode) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<Furit> filterList = furitList.stream()
                .filter(item -> FULL_REDUCE_LIST.contains(item.getDiscountType()))
                .collect(Collectors.toList());

        ReduceRulesEnum rulesEnum = ReduceRulesEnum.findEnumByCode(ruleCode);
        for (Furit furit : filterList) {
            getTotalPrice(furit);
            totalPrice = totalPrice.add(furit.getTotalPrice());
        }
        BigDecimal divide = totalPrice.divide(rulesEnum.getFullAmount(), 0, BigDecimal.ROUND_DOWN);
        BigDecimal retNum = divide.multiply(rulesEnum.getReduceAmount());
        retNum = totalPrice.subtract(retNum);
        System.out.println("满减前金额: " + totalPrice + "，满减后金额: " + retNum);

        return retNum;
    }

    /*
     * 计算折扣跟满减金额
     * 例如：满100减10
     * */
    public static BigDecimal getTotalPriceAndFullReduce(List<Furit> furitList, String ruleCode) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        String fullCode = DiscountTypeEnum.FULL_REDUCTION.getCode();
        List<Furit> filterList = furitList.stream()
                .filter(item -> !fullCode.equals(item.getDiscountType()))
                .collect(Collectors.toList());

        for (Furit furit : filterList) {
            getTotalPrice(furit);
            if (NOT_FULL_REDUCE.contains(furit.getDiscountType())) {
                totalPrice = totalPrice.add(furit.getTotalPrice());
            }
        }
        System.out.println("非满减金额: " + totalPrice);

        BigDecimal fullReduce = getFullReduce(furitList, ruleCode);
        totalPrice = totalPrice.add(fullReduce);

        return totalPrice;
    }

    /*
     * 计算折扣跟满减金额
     * 例如：每满100减10
     * */
    public static BigDecimal getManyTotalPriceAndFullReduce(List<Furit> furitList, String ruleCode) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        String fullCode = DiscountTypeEnum.FULL_REDUCTION.getCode();
        List<Furit> filterList = furitList.stream()
                .filter(item -> !fullCode.equals(item.getDiscountType()))
                .collect(Collectors.toList());

        for (Furit furit : filterList) {
            getTotalPrice(furit);
            if (NOT_FULL_REDUCE.contains(furit.getDiscountType())) {
                totalPrice = totalPrice.add(furit.getTotalPrice());
            }
        }
        System.out.println("非满减金额: " + totalPrice);

        BigDecimal fullReduce = getManyFullReduce(furitList, ruleCode);
        totalPrice = totalPrice.add(fullReduce);

        return totalPrice;
    }

}
