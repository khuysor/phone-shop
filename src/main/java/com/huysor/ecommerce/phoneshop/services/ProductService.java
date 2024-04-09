package com.huysor.ecommerce.phoneshop.services;

import com.huysor.ecommerce.phoneshop.dto.ProductImportDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Product getProduct(Long id);
    Product save(Product product);
    List<Product> getAllProduct();
    void importProduct(ProductImportDTO productImportDTO);
    void setPrice(Long id, BigDecimal Price);
    void validateStock(Long id,Integer unit);
    Map<Integer,String> uploadProduct(MultipartFile file);
    Product getByModelIdAndColorId(Long modelId, Long colorId);
}
