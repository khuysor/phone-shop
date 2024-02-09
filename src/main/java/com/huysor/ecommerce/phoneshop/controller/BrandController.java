package com.huysor.ecommerce.phoneshop.controller;

import com.huysor.ecommerce.phoneshop.dto.BrandDTO;
import com.huysor.ecommerce.phoneshop.entity.Brands;
import com.huysor.ecommerce.phoneshop.entity.Model;
import com.huysor.ecommerce.phoneshop.services.BrandService;
import com.huysor.ecommerce.phoneshop.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    // post method
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){

        Brands brands= Mapper.toBrand(brandDTO);
        brands = brandService.create(brands);
        // to respon all data from brands
//        return ResponseEntity.ok(brands) ;

        // to respon body with id
        return ResponseEntity.ok(Mapper.toBrandDTO(brands)) ;
    }
    @GetMapping("{id}")
    public ResponseEntity<?>getOneBrand(@PathVariable("id") Integer brand_id){
      Brands brands=  brandService.getBrandById(brand_id);
        return ResponseEntity.ok(Mapper.toBrandDTO(brands));
    }
    @PutMapping("{id}")
    public ResponseEntity<?>updateBrand(@PathVariable("id") Integer brand_id,@RequestBody BrandDTO brandDTO){
        Brands brands =  Mapper.toBrand(brandDTO);
        Brands updated= brandService.update(brand_id,brands);
        return ResponseEntity.ok(Mapper.toBrandDTO(updated));
    }

}
