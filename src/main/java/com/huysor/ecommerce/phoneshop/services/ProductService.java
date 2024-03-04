package com.huysor.ecommerce.phoneshop.services;

import com.huysor.ecommerce.phoneshop.dto.ProductImportDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;

public interface ProductService {
    Product getProduct(Long id);
    Product save(Product product);
    void importProduct(ProductImportDTO productImportDTO);
}
