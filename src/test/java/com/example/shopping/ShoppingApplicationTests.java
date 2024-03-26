package com.example.shopping;

import com.example.shopping.entity.Apple;
import com.example.shopping.entity.Furit;
import com.example.shopping.entity.Mango;
import com.example.shopping.entity.Strawberry;
import com.example.shopping.enums.DiscountTypeEnum;
import com.example.shopping.impl.SellFruitServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ShoppingApplicationTests {

    @Autowired
    private Strawberry strawberry;

    @Autowired
    private Mango mango;

    @Autowired
    private Apple apple;

    @Autowired
    SellFruitServiceImpl service;

    /*
     * 题目1，假设购买5斤苹果，3斤草莓
     * 计算总价
     * */
    @Test
    public void testCase1() {

        List<Furit> furitList = initData1();
        BigDecimal totalFruitPrice = service.getTotalFruitPrice(furitList);
        System.out.println("总价(元): " + totalFruitPrice);
    }

    /*
     * 题目2，假设购买4斤苹果，5斤草莓，2斤芒果
     * 计算总价
     * */
    @Test
    public void testCase2() {
        List<Furit> furitList = initData2();
        BigDecimal totalFruitPrice = service.getTotalFruitPrice(furitList);
        System.out.println("总价(元): " + totalFruitPrice);
    }

    /*
     * 题目3，假设购买4斤苹果，5斤草莓(打8折)，2斤芒果
     * 计算总价
     * */
    @Test
    public void testCase3() {

        List<Furit> furitList = initData3();
        BigDecimal totalFruitPrice = service.getTotalFruitPrice(furitList);
        System.out.println("总价(元): " + totalFruitPrice);
    }

    /*
     * 题目4，假设购买6斤苹果，5斤草莓，5斤芒果，满100减10
     * 计算总价
     * 满减说明：99.99不享受满减，100,100.1享受满减
     * */
    @Test
    public void testCase4() {

        List<Furit> furitList = initData4();
        BigDecimal totalFruitPrice = service.getTotalPriceAndFullReduce(furitList);
        System.out.println("总价(元): " + totalFruitPrice);

    }


    public List<Furit> initData1() {
        List<Furit> furitList = new ArrayList<>();

        //苹果
        apple.setNumber(new BigDecimal("5"));
        furitList.add(apple);

        //草莓
        strawberry.setNumber(new BigDecimal("3"));
        furitList.add(strawberry);

        return furitList;
    }

    public List<Furit> initData2() {
        List<Furit> furitList = new ArrayList<>();

        //苹果
        apple.setNumber(new BigDecimal("4"));
        furitList.add(apple);

        //草莓
        strawberry.setNumber(new BigDecimal("5"));
        furitList.add(strawberry);

        //芒果
        mango.setNumber(new BigDecimal("2"));
        furitList.add(mango);

        return furitList;
    }

    public List<Furit> initData3() {
        List<Furit> furitList = new ArrayList<>();

        //苹果
        apple.setNumber(new BigDecimal("4"));
        furitList.add(apple);

        //草莓
        strawberry.setNumber(new BigDecimal("5"));
        strawberry.setDiscount(new BigDecimal("0.8"));
        strawberry.setDiscountType(DiscountTypeEnum.DISCOUNT.getCode());
        furitList.add(strawberry);

        //芒果
        mango.setNumber(new BigDecimal("2"));
        furitList.add(mango);

        return furitList;
    }

    public List<Furit> initData4() {
        List<Furit> furitList = new ArrayList<>();

        //苹果
        apple.setNumber(new BigDecimal("6"));
        apple.setDiscountType(DiscountTypeEnum.FULL_REDUCTION.getCode());
//        apple.setDiscount(new BigDecimal("0.8"));

        furitList.add(apple);

        //草莓
        strawberry.setNumber(new BigDecimal("5"));
        strawberry.setDiscountType(DiscountTypeEnum.FULL_REDUCTION.getCode());
//        strawberry.setDiscount(new BigDecimal("0.9"));
        furitList.add(strawberry);

        //芒果
        mango.setNumber(new BigDecimal("5"));
        mango.setDiscountType(DiscountTypeEnum.FULL_REDUCTION.getCode());
        furitList.add(mango);

        return furitList;
    }
}
