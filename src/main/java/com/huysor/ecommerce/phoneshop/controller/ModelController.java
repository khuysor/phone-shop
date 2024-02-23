package com.huysor.ecommerce.phoneshop.controller;

import com.huysor.ecommerce.phoneshop.Mapper.ModelMapper;
import com.huysor.ecommerce.phoneshop.dto.ModelDTO;
import com.huysor.ecommerce.phoneshop.entity.Model;
import com.huysor.ecommerce.phoneshop.services.ModelService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/model")
public class ModelController  {

    private final ModelService modelService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?>create(@RequestBody ModelDTO modelDTO){
        Model model=modelMapper.toModel(modelDTO);
        model=modelService.save(model);
        return ResponseEntity.ok(modelMapper.toModelDTO(model));
    }

    @GetMapping("{id}")
    public ResponseEntity<?>getOneModel(@PathVariable("id")Integer id){
        Model model= modelService.getModelById(id);
        return ResponseEntity.ok(modelMapper.toModelDTO(model));
    }

}
