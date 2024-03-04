package com.huysor.ecommerce.phoneshop.controller;

import com.huysor.ecommerce.phoneshop.Mapper.ProductMapper;
import com.huysor.ecommerce.phoneshop.dto.ProductDTO;
import com.huysor.ecommerce.phoneshop.dto.ProductImportDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.services.ModelService;
import com.huysor.ecommerce.phoneshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ModelService modelService;

    @PostMapping

    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {

        Product product = productMapper.toProduct(productDTO);
        product = productService.save(product);
        return ResponseEntity.ok(product);
    }

    @PostMapping("import")
    public ResponseEntity<?>importProduct(@RequestBody @Valid ProductImportDTO productImportDTO){

        productService.importProduct(productImportDTO);
       return ResponseEntity.ok().build();
    }
}
