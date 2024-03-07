package com.huysor.ecommerce.phoneshop.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class SaleDTO {
    @NotNull
    private List<ProductSoldDTO> productSoldDTOS;
    private LocalDateTime saleDate;
}
