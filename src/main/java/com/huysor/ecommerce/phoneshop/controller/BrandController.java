package com.huysor.ecommerce.phoneshop.controller;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.huysor.ecommerce.phoneshop.Mapper.ModelEntityMapper;
import com.huysor.ecommerce.phoneshop.dto.ModelDTO;
import com.huysor.ecommerce.phoneshop.dto.PageDTO;
import com.huysor.ecommerce.phoneshop.entity.Model;
import com.huysor.ecommerce.phoneshop.services.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("brand")
@Secured("ROLE_SALE")// we can use like this
public class BrandController {
	private final BrandService brandService;
	private final ModelService modelService;
	private final ModelEntityMapper modelEntityMapper;

	@PreAuthorize("hasAuthority('brand:write')") //or like this
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brands brands = BrandMapper.INSTANCE.toBrand(brandDTO);
		brands = brandService.create(brands);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brands));
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Long brand_id) {
		Brands brands = brandService.getBrandById(brand_id);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brands));
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateBrand(@PathVariable("id") Long brand_id, @RequestBody BrandDTO brandDTO) {
		Brands brands = BrandMapper.INSTANCE.toBrand(brandDTO);
		Brands updated = brandService.update(brand_id, brands);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updated));
	}


	@GetMapping
	public ResponseEntity<?> getbrands(@RequestParam Map<String, String> params) {
		Page<Brands> brand=brandService.getBrands(params);
		PageDTO pageDTO=new PageDTO(brand);
		return ResponseEntity.ok(pageDTO);
	}
	@GetMapping("{id}/models")
	public  ResponseEntity<?>getModelByBrandID(@PathVariable("id")Long brandId){

		List<Model> brands= modelService.getModelByBrandId(brandId);
		List<ModelDTO> model=brands.stream().map(modelEntityMapper::toModelDTO).toList();
		return ResponseEntity.ok(model);
	}

}
