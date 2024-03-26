package com.example.shopping.service;


import com.example.shopping.entity.Furit;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface SellFruitService {

    public BigDecimal getFruitPrice(Furit furit);
}
