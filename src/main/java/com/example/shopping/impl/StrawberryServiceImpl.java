package com.example.shopping.impl;

import com.example.shopping.entity.Furit;
import com.example.shopping.entity.Mango;
import com.example.shopping.entity.Strawberry;
import com.example.shopping.enums.FuritEnum;
import com.example.shopping.service.SellFruitService;
import com.example.shopping.util.CalculateUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("STRAWBERRY")
public class StrawberryServiceImpl implements SellFruitService {

    @Override
    public BigDecimal getFruitPrice(Furit furit) {
        CalculateUtil.getTotalPrice(furit);
        BigDecimal discountPrice = furit.getTotalPrice();
        System.out.println("草莓总价(元): " + discountPrice);
        return discountPrice;
    }
}
