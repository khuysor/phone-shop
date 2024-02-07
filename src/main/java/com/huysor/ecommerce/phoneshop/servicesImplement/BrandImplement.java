package com.huysor.ecommerce.phoneshop.servicesImplement;

import com.huysor.ecommerce.phoneshop.entity.Brands;
import com.huysor.ecommerce.phoneshop.repository.BrandRepository;
import com.huysor.ecommerce.phoneshop.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandImplement implements BrandService {
    @Autowired
   protected BrandRepository brandRepository;
    @Override
    public Brands create(Brands brands) {
        return brandRepository.save(brands);
    }
}
