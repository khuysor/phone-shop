package com.huysor.ecommerce.phoneshop.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huysor.ecommerce.phoneshop.Mapper.ModelEntityMapper;
import com.huysor.ecommerce.phoneshop.dto.ModelDTO;
import com.huysor.ecommerce.phoneshop.dto.PageDTO;
import com.huysor.ecommerce.phoneshop.entity.AttachmentFile;
import com.huysor.ecommerce.phoneshop.entity.Model;
import com.huysor.ecommerce.phoneshop.services.AttachmentService;
import com.huysor.ecommerce.phoneshop.services.ModelService;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.huysor.ecommerce.phoneshop.Mapper.BrandMapper;
import com.huysor.ecommerce.phoneshop.dto.BrandDTO;
import com.huysor.ecommerce.phoneshop.entity.Brands;
import com.huysor.ecommerce.phoneshop.services.BrandService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("brand")
@Secured("ROLE_SALE")// we can use like this
public class BrandController {
	private final BrandService brandService;
	private final ModelService modelService;
	private final ModelEntityMapper modelEntityMapper;
	private final AttachmentService attachmentService;


	@PreAuthorize("hasAuthority('brand:write')") //or like this
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brands brands = BrandMapper.INSTANCE.toBrand(brandDTO);
		brands = brandService.create(brands);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brands));
	}
	@PreAuthorize("hasAuthority('brand:read')")
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
	@PostMapping("upload")
	public ResponseEntity<?>uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
		AttachmentFile attachmentFile=null;
		String downLoadUrl="";
		attachmentFile=attachmentService.upload(file);
		downLoadUrl= ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/brand/upload/"+String.valueOf(attachmentFile.getId()))
				.toUriString();
		List list= List.of(attachmentFile, downLoadUrl);
		return ResponseEntity.ok(list);
	}
	@GetMapping("upload/{name}")
	public ResponseEntity<?>getFileUpload(@PathVariable("name")String fileName) throws Exception {
		AttachmentFile file= attachmentService.getFileUpload(fileName);
		ResponseEntity<ByteArrayResource> body = ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(file.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + file.getName() + "\"")
				.body(new ByteArrayResource(file.getFileData()));
		return body;
	}


}
