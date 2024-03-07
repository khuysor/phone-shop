package com.huysor.ecommerce.phoneshop.controller;

import com.huysor.ecommerce.phoneshop.dto.SaleDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "sale")
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<?> saleProduct(@RequestBody SaleDTO saleDTO) {
        saleService.sell(saleDTO);
        return ResponseEntity.ok().build();
    }
}
