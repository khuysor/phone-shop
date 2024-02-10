package com.huysor.ecommerce.phoneshop.Mapper;

import org.mapstruct.Mapper;

import com.huysor.ecommerce.phoneshop.dto.BrandDTO;
import com.huysor.ecommerce.phoneshop.entity.Brands;

@Mapper
public interface BrandMapper {

    Brands toBrand(BrandDTO brandDTO);
}
