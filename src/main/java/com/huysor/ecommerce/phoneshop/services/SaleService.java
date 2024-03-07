package com.huysor.ecommerce.phoneshop.services;

import com.huysor.ecommerce.phoneshop.dto.SaleDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SaleService {
    void sell(SaleDTO saleDTO);
    List<SaleDTO> getAllsale();
}
