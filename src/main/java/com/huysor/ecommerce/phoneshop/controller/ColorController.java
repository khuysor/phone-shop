package com.huysor.ecommerce.phoneshop.controller;

import com.huysor.ecommerce.phoneshop.entity.Color;
import com.huysor.ecommerce.phoneshop.services.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("color")
@RestController
public class ColorController {
    private  final ColorService colorService;
    public ResponseEntity<?> create(@RequestBody Color color){
        Color color1=colorService.save(color);
        return ResponseEntity.ok(color1);
    }
}
