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
public class Mango extends Furit {
    public Mango() {
        super.name = FuritEnum.MANGO.getName();
        super.code = FuritEnum.MANGO.getCode();
        super.price = FuritEnum.MANGO.getPrice();
        super.discount = BigDecimal.ONE;
        super.discountType = DiscountTypeEnum.NONE.getCode();
    }

    public Mango(String name, String price, String discount, String discountType) {
        super.name = StringUtils.isEmpty(name) ? FuritEnum.MANGO.getName() : name;
        super.code = FuritEnum.MANGO.getCode();
        super.price = new BigDecimal(price);
        super.discount = new BigDecimal(discount);
        super.discountType = discountType;
    }

}
