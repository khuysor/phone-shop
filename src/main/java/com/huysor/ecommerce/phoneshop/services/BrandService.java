package com.huysor.ecommerce.phoneshop.services;

import com.huysor.ecommerce.phoneshop.entity.Brands;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    Brands create(Brands brands);
   Brands getBrandById(Integer id);
   Brands update(Integer id,Brands brands);
}
