package com.example.shopping.impl;

import com.example.shopping.entity.Apple;
import com.example.shopping.entity.Furit;
import com.example.shopping.entity.Mango;
import com.example.shopping.enums.FuritEnum;
import com.example.shopping.service.SellFruitService;
import com.example.shopping.util.CalculateUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("MANGO")
public class MangoServiceImpl implements SellFruitService {

    @Override
    public BigDecimal getFruitPrice(Furit furit) {
        CalculateUtil.getTotalPrice(furit);
        BigDecimal totalPrice = furit.getTotalPrice();
        System.out.println("芒果总价(元): " + totalPrice);
        return totalPrice;
    }
}
