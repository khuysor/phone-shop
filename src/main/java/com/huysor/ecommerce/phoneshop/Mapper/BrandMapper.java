package com.huysor.ecommerce.phoneshop.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.huysor.ecommerce.phoneshop.dto.BrandDTO;
import com.huysor.ecommerce.phoneshop.entity.Brands;

@Mapper
public interface BrandMapper {
	BrandMapper INSTANCE= Mappers.getMapper(BrandMapper.class);

    Brands toBrand(BrandDTO dto);

    BrandDTO toBrandDTO(Brands entity);
}
