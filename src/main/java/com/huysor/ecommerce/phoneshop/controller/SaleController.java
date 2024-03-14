package com.huysor.ecommerce.phoneshop.controller;

import com.huysor.ecommerce.phoneshop.dto.SaleDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.entity.SaleDetail;
import com.huysor.ecommerce.phoneshop.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


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

    @PutMapping("{saleId}/cancel")
    public ResponseEntity<?>cancelSale(@PathVariable Long saleId){
        saleService.cancelSale(saleId);
    return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<?>getAllproduct(){
        List<SaleDetail> saleDetailList=saleService.getAllsale();
        return ResponseEntity.ok(saleDetailList);
    }

}
