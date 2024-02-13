package com.huysor.ecommerce.phoneshop.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huysor.ecommerce.phoneshop.Mapper.BrandMapper;
import com.huysor.ecommerce.phoneshop.dto.BrandDTO;
import com.huysor.ecommerce.phoneshop.entity.Brands;
import com.huysor.ecommerce.phoneshop.services.BrandService;

@RestController
@RequestMapping("brand")
public class BrandController {
	@Autowired
	private BrandService brandService;

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

	@GetMapping
	public ResponseEntity<?> getbrands(@RequestParam Map<String, String> params) {
		List<BrandDTO> list =
				brandService.getBrands(params)
				.stream()
				.map(brands -> BrandMapper.INSTANCE.toBrandDTO(brands))
				.collect(Collectors.toList());

		return ResponseEntity.ok(list);
	}

}
