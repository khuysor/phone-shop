package com.huysor.ecommerce.phoneshop.services;

import org.springframework.stereotype.Service;

import com.huysor.ecommerce.phoneshop.entity.Brands;

@Service
public interface BrandService {
	Brands create(Brands brands);

	Brands getBrandById(Integer id);

	Brands update(Integer id, Brands brands);
}
