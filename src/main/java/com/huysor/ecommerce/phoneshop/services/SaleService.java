package com.huysor.ecommerce.phoneshop.services;

import com.huysor.ecommerce.phoneshop.dto.SaleDTO;
import com.huysor.ecommerce.phoneshop.entity.Sale;
import com.huysor.ecommerce.phoneshop.entity.SaleDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SaleService {
    void sell(SaleDTO saleDTO);

    List<SaleDetail> getAllsale();

    Sale getById(Long saleId);
    void cancelSale(Long saleId);
}
