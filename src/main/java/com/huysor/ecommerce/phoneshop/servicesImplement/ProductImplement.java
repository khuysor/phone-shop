package com.huysor.ecommerce.phoneshop.servicesImplement;

import com.huysor.ecommerce.phoneshop.Mapper.ProductMapper;
import com.huysor.ecommerce.phoneshop.dto.ProductImportDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.entity.ProductImportHistory;
import com.huysor.ecommerce.phoneshop.exception.ResourceNotFoundException;
import com.huysor.ecommerce.phoneshop.repository.ProductImportRepository;
import com.huysor.ecommerce.phoneshop.repository.ProductRepository;
import com.huysor.ecommerce.phoneshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ProductImplement implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImportRepository productImportRepository;
    private final ProductMapper productMapper;

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    @Override
    public Product save(Product product) {
        String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public void importProduct(ProductImportDTO productImportDTO) {
        Product product = getProduct(productImportDTO.getProductId());
        Integer available=0;
        if(product.getAvailableUnit()!=null){
            available = product.getAvailableUnit();
        }
        product.setAvailableUnit(available+productImportDTO.getImportUnit());
        productRepository.save(product);
        ProductImportHistory productImportHistory = productMapper.toProduct(productImportDTO);
        productImportRepository.save(productImportHistory);
    }

    @Override
    public void setPrice(Long id, BigDecimal Price) {
    Product product=  getProduct(id);
    product.setSalePrice(Price);
    productRepository.save(product);
    }

    @Override
    public void validateStock(Long id, Integer unit) {

    }
}
