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
public class Apple extends Furit {
    public Apple() {
        super.name = FuritEnum.APPLE.getName();
        super.code = FuritEnum.APPLE.getCode();
        super.price = FuritEnum.APPLE.getPrice();
        super.discount = BigDecimal.ONE;
        super.discountType = DiscountTypeEnum.NONE.getCode();
    }

    public Apple(String name, String price, String discount, String discountType) {
        super.name = StringUtils.isEmpty(name) ? FuritEnum.APPLE.getName() : name;
        super.code = FuritEnum.APPLE.getCode();
        super.price = new BigDecimal(price);
        super.discount = new BigDecimal(discount);
        super.discountType = discountType;
    }

}
