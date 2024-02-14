package com.huysor.ecommerce.phoneshop.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.huysor.ecommerce.phoneshop.entity.Brands;

@Service
public interface BrandService {
	Brands create(Brands brands);

	Brands getBrandById(Integer id);

	Brands update(Integer id, Brands brands);

	Page<Brands> getBrands(Map<String, String> params);
}
