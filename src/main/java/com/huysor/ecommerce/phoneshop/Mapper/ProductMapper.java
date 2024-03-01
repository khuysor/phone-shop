package com.huysor.ecommerce.phoneshop.Mapper;

import com.huysor.ecommerce.phoneshop.dto.ProductDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.services.ColorService;
import com.huysor.ecommerce.phoneshop.services.ModelService;
import com.huysor.ecommerce.phoneshop.services.ProductService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {ModelService.class, ColorService.class})
public interface ProductMapper {
    @Mapping(target = "model",source = "modelId")
    @Mapping(target = "color",source = "colorId")
   Product toProduct(ProductDTO productDTO);
//   ProductDTO toProductDto(Product product);

}
