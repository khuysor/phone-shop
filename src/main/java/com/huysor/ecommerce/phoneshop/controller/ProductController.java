package com.huysor.ecommerce.phoneshop.controller;

import com.huysor.ecommerce.phoneshop.Mapper.ProductMapper;
import com.huysor.ecommerce.phoneshop.dto.ProductDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.services.ModelService;
import com.huysor.ecommerce.phoneshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ModelService modelService;
@PostMapping
    public ResponseEntity<?>create(@RequestBody ProductDTO productDTO){
        System.out.println("\n"+productDTO+"\n");
        Product product=productMapper.toProduct(productDTO);
        product=productService.save(product);
        return ResponseEntity.ok(product);
    }
}
