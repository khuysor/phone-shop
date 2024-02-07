package com.huysor.ecommerce.phoneshop.util;

import com.huysor.ecommerce.phoneshop.dto.BrandDTO;
import com.huysor.ecommerce.phoneshop.entity.Brands;

public class Mapper {
  public static  Brands toBrand(BrandDTO brandDTO){
      Brands brands=new Brands();
      brands.setId(brandDTO.getId());
      brands.setName(brandDTO.getName());
      return brands;
  }
}
