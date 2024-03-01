package com.huysor.ecommerce.phoneshop.servicesImplement;

import java.util.Map;
import com.huysor.ecommerce.phoneshop.services.util.PageUntil;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.huysor.ecommerce.phoneshop.entity.Brands;
import com.huysor.ecommerce.phoneshop.exception.ResourceNotFoundException;
import com.huysor.ecommerce.phoneshop.repository.BrandRepository;
import com.huysor.ecommerce.phoneshop.services.BrandService;
import com.huysor.ecommerce.phoneshop.spec.BrandSpec;
import com.huysor.ecommerce.phoneshop.spec.BrandFilter;

@Service
@RequiredArgsConstructor
public class BrandImplement implements BrandService {
	@Autowired
	private final BrandRepository brandRepository;


	@Override
	public Brands create(Brands brands) {
		return brandRepository.save(brands);
	}

	@Override
	public Brands getBrandById(Long id) {

		return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("brand", id));
	}

	@Override
	public Brands update(Long id, Brands brandsUpdate) {
		Brands brands = getBrandById(id);
		brands.setName(brandsUpdate.getName());
		return brandRepository.save(brands);
	} 

	@Override
	public Page <Brands> getBrands(Map<String, String> params) {
		BrandFilter brandFilter =new BrandFilter();
		if (params.containsKey("name")) {
		String name = params.get("name");
		brandFilter.setName(name);
		}
		if(params.containsKey("id")) {
		Integer id= Integer.parseInt(params.get("id"));
			brandFilter.setId(id);
		}
		BrandSpec brandSpec= new BrandSpec(brandFilter);
		int pageNumber=1;
		if(params.containsKey(PageUntil.pageNumber)){
			pageNumber=Integer.parseInt(params.get(PageUntil.pageNumber));
		}
		int pageSize=PageUntil.DEFAULT_PAGE_LIMIT;
		if (params.containsKey(PageUntil.pageSize)){
			pageSize=Integer.parseInt(params.get(PageUntil.pageSize));
		}

		Pageable pageable=PageUntil.getPageAble(pageNumber,pageSize);
		Page<Brands> page = brandRepository.findAll(brandSpec, pageable);
		return page;
	}

	
	




}
