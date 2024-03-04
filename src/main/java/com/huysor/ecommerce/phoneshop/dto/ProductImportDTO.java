package com.huysor.ecommerce.phoneshop.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class ProductImportDTO {
    @NotNull(message = "product not null")
    private Long productId;
    @Min(value = 1,message = "Product unit must be at least one")
    private Integer importUnit;
    private BigDecimal pricePerUnit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateImport;
}
