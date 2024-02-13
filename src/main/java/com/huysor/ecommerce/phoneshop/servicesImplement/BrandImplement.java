package com.huysor.ecommerce.phoneshop.servicesImplement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huysor.ecommerce.phoneshop.entity.Brands;
import com.huysor.ecommerce.phoneshop.exception.ResourceNotFoundException;
import com.huysor.ecommerce.phoneshop.repository.BrandRepository;
import com.huysor.ecommerce.phoneshop.services.BrandService;
import com.huysor.ecommerce.phoneshop.spec.BrandSpec;
import com.huysor.ecommerce.phoneshop.spec.BrandFilter;

@Service
public class BrandImplement implements BrandService {
	@Autowired
	protected BrandRepository brandRepository;


	@Override
	public Brands create(Brands brands) {
		return brandRepository.save(brands);
	}

	@Override
	public Brands getBrandById(Integer id) {
//        basic concept
//       Optional <Brands> optionalBrands= brandRepository.findById(id);
//       if(optionalBrands.isPresent()){
//           return optionalBrands.get();
//       }
//        throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Brand with %d Not Found".formatted(id));

//        intermediate concept
//        return brandRepository.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,String.format("Brand with id %d not found",id)));

//        oop concept
		return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("brand", id));
	}

	@Override
	public Brands update(Integer id, Brands brandsUpdate) {
		Brands brands = getBrandById(id);
		brands.setName(brandsUpdate.getName());
		return brandRepository.save(brands);
	} 

	@Override
	public List<Brands> getBrands(Map<String, String> params) {
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
		return brandRepository.findAll(brandSpec);
	}
	
	




}
