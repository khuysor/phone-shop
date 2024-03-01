package com.huysor.ecommerce.phoneshop.dto;

import com.huysor.ecommerce.phoneshop.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ImportProductDTO {
    private Long productId;
    private Integer importUnit;
    private BigDecimal pricePerUnit;
    private LocalDateTime dateImport;
}
