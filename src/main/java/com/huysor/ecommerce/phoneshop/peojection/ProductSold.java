package com.huysor.ecommerce.phoneshop.peojection;

import java.math.BigDecimal;

public interface ProductSold {
    Long getProductId();
    String getProductName();
    Integer getUnit();
    BigDecimal getAmount();
    String getColor();
}
