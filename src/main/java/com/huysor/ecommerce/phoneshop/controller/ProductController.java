package com.huysor.ecommerce.phoneshop.controller;

import com.huysor.ecommerce.phoneshop.Mapper.ProductMapper;
import com.huysor.ecommerce.phoneshop.dto.PriceDTO;
import com.huysor.ecommerce.phoneshop.dto.ProductDTO;
import com.huysor.ecommerce.phoneshop.dto.ProductImportDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.services.ModelService;
import com.huysor.ecommerce.phoneshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
@Secured("ROLE_SALE")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ModelService modelService;

    @GetMapping
    public ResponseEntity<?>getAllProduct(){
        List<Product>list= productService.getAllProduct();
        return ResponseEntity.ok(list);

    }

    @PostMapping

    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {

        Product product = productMapper.toProduct(productDTO);
        product = productService.save(product);
        return ResponseEntity.ok(product);
    }

    @PostMapping("import")
    public ResponseEntity<?> importProduct(@RequestBody @Valid ProductImportDTO productImportDTO) {

        productService.importProduct(productImportDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/setPrice")
    public ResponseEntity<?> setPrice(@PathVariable Long id, @RequestBody @Valid PriceDTO priceDTO) {
        productService.setPrice(id, priceDTO.getPrice());
        return ResponseEntity.ok().build();
    }

    @PostMapping("uploadProduct")
    public ResponseEntity<?> writeDatafromExcel(@RequestParam("file") MultipartFile file) {
        Map<Integer, String> errMap = productService.uploadProduct(file);
        return ResponseEntity.ok(errMap);
    }
}
