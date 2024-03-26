package com.example.shopping.entity;

import com.example.shopping.enums.DiscountTypeEnum;
import com.example.shopping.enums.FuritEnum;
import com.microsoft.sqlserver.jdbc.StringUtils;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@Component
public class Strawberry extends Furit {
    public Strawberry() {
        super.name = FuritEnum.STRAWBERRY.getName();
        super.code = FuritEnum.STRAWBERRY.getCode();
        super.price = FuritEnum.STRAWBERRY.getPrice();
        super.discount = BigDecimal.ONE;
        super.discountType = DiscountTypeEnum.NONE.getCode();
    }

    public Strawberry(String name, String price, String discount, String discountType) {
        super.name = StringUtils.isEmpty(name) ? FuritEnum.STRAWBERRY.getName() : name;
        super.code = FuritEnum.STRAWBERRY.getCode();
        super.price = new BigDecimal(price);
        super.discount = new BigDecimal(discount);
        super.discountType = discountType;

    }

}
