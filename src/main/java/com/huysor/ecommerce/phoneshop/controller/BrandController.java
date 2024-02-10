package com.huysor.ecommerce.phoneshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.huysor.ecommerce.phoneshop.Mapper.BrandMapper;
import com.huysor.ecommerce.phoneshop.dto.BrandDTO;
import com.huysor.ecommerce.phoneshop.entity.Brands;
import com.huysor.ecommerce.phoneshop.services.BrandService;

import java.util.Map;

@RestController
@RequestMapping("brand")
public class BrandController {
	@Autowired
	private BrandService brandService;

	// post method
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brands brands = BrandMapper.INSTANCE.toBrand(brandDTO);
		brands = brandService.create(brands);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brands));
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brand_id) {
		Brands brands = brandService.getBrandById(brand_id);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brands));
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateBrand(@PathVariable("id") Integer brand_id, @RequestBody BrandDTO brandDTO) {
		Brands brands = BrandMapper.INSTANCE.toBrand(brandDTO);
		Brands updated = brandService.update(brand_id, brands);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updated));
	}

	@RequestMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params) {

		return null;
	}

}
