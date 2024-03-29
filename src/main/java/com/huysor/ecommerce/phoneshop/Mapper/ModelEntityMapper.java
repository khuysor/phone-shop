package com.huysor.ecommerce.phoneshop.Mapper;

import com.huysor.ecommerce.phoneshop.dto.ModelDTO;
import com.huysor.ecommerce.phoneshop.entity.Model;
import com.huysor.ecommerce.phoneshop.services.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring",uses = {BrandService.class})
public interface ModelEntityMapper {
    ModelEntityMapper INSTANCE   = Mappers.getMapper(ModelEntityMapper.class);

//    ModelDTO toModelDto(Model model);
    @Mapping(target = "brands",source = "brandId")
    Model toModel(ModelDTO modelDTO);

    @Mapping(target = "brandId",source = "brands.id")
    ModelDTO toModelDTO(Model model);
//    default Brands toBrandId(Integer id){
//        Brands brands=new Brands();
//        brands.setId(id);
//         return brands;
//    }
}
