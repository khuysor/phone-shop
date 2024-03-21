package com.huysor.ecommerce.phoneshop.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductReportDTO {
    private Long id;
    private String productName;
    private Integer unit;
    private BigDecimal amount;
}

