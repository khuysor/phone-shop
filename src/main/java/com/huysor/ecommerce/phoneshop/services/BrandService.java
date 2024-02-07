package com.huysor.ecommerce.phoneshop.services;

import com.huysor.ecommerce.phoneshop.entity.Brands;
import org.springframework.stereotype.Service;

@Service
public interface BrandService {
    Brands create(Brands brands);

}
