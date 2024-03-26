package com.example.shopping.impl;

import com.example.shopping.entity.Furit;
import com.example.shopping.enums.FuritEnum;
import com.example.shopping.enums.ReduceRulesEnum;
import com.example.shopping.service.FruitContext;
import com.example.shopping.service.SellFruitService;
import com.example.shopping.util.CalculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SellFruitServiceImpl {
    @Autowired
    FruitContext fruitContext;

    /*
     * 计算单种水果价格
     * @param furit: 水果对象
     * @return: 单种水果价格
     * */
    public BigDecimal getSingleFuritPrice(Furit furit) {

        SellFruitService furitService = fruitContext.getFuritService(furit.getCode());
        return furitService.getFruitPrice(furit);
    }

    /*
     * 计算多种水果总价
     * @param furitList: 水果对象集合
     * @return: 总价
     * */
    public BigDecimal getTotalFruitPrice(List<Furit> furitList) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Furit furit : furitList) {
            SellFruitService furitService = fruitContext.getFuritService(furit.getCode());
            BigDecimal price = furitService.getFruitPrice(furit);
            totalPrice = totalPrice.add(price);
        }
        return totalPrice;
    }

    /*
     * 计算多种水果总价
     * @param furitList: 水果对象集合，满减
     * @return: 总价
     * */
    public BigDecimal getTotalPriceAndFullReduce(List<Furit> furitList) {
        BigDecimal totalPrice = CalculateUtil.getTotalPriceAndFullReduce(
                furitList, ReduceRulesEnum.RULE_100_10.getCode());
        return totalPrice;
    }

    /*
     * 计算多种水果总价
     * @param furitList: 水果对象集合，每满减多少
     * @return: 总价
     * */
    public BigDecimal getManyTotalPriceAndFullReduce(List<Furit> furitList) {
        BigDecimal totalPrice = CalculateUtil.getManyTotalPriceAndFullReduce(
                furitList, ReduceRulesEnum.RULE_100_10.getCode());
        return totalPrice;
    }

}
