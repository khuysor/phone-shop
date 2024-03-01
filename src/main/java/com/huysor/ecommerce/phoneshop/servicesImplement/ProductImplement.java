package com.huysor.ecommerce.phoneshop.servicesImplement;

import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.exception.ResourceNotFoundException;
import com.huysor.ecommerce.phoneshop.repository.ProductRepository;
import com.huysor.ecommerce.phoneshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ProductImplement implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product",id));
    }

    @Override
    public Product save(Product product) {
        String name="%s %s".formatted( product.getModel().getName(),product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }
}
