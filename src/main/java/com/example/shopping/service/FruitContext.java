package com.example.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FruitContext {
    @Autowired
    Map<String, SellFruitService> fruitMap;

    public SellFruitService getFuritService(String code) {
        SellFruitService baseService = fruitMap.get(code);
        return baseService;
    }

}