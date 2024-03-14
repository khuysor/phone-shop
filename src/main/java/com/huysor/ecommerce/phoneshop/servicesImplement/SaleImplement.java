package com.huysor.ecommerce.phoneshop.servicesImplement;

import com.huysor.ecommerce.phoneshop.dto.ProductSoldDTO;
import com.huysor.ecommerce.phoneshop.dto.SaleDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.entity.Sale;
import com.huysor.ecommerce.phoneshop.entity.SaleDetail;
import com.huysor.ecommerce.phoneshop.exception.ApiException;
import com.huysor.ecommerce.phoneshop.exception.ResourceNotFoundException;
import com.huysor.ecommerce.phoneshop.repository.ProductRepository;
import com.huysor.ecommerce.phoneshop.repository.SaleDetailRepository;
import com.huysor.ecommerce.phoneshop.repository.SaleRepository;
import com.huysor.ecommerce.phoneshop.services.ProductService;
import com.huysor.ecommerce.phoneshop.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleImplement implements SaleService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    @Override
    public void sell(SaleDTO saleDTO) {
        List<Long> productId = saleDTO.getProductSoldDTOS().stream()
                .map(ProductSoldDTO::getProductId).toList();

        productId.forEach(productService::getProduct);
        List<Product> products = productRepository.findAllById(productId);
        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        // validate stock
        saleDTO.getProductSoldDTOS().forEach(
                ps -> {
                    Product product = productMap.get(ps.getProductId());
                    if (product.getAvailableUnit() < ps.getQuantity()) {
                        throw new ApiException(HttpStatus.BAD_REQUEST, "Product[%s] have not enough in stock".formatted(product.getName()));
                    }
                }
        );

        // sale save
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);

        // sale sale Detail

        saleDTO.getProductSoldDTOS().forEach(ps -> {
            Product product = productMap.get(ps.getProductId());

            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setPrice(product.getSalePrice().multiply(BigDecimal.valueOf(ps.getQuantity())));
            saleDetail.setProduct(product);
            saleDetail.setSale(sale);
            saleDetail.setUnit(ps.getQuantity());
            product.setAvailableUnit(product.getAvailableUnit() - ps.getQuantity());
            productService.save(product);
            saleDetailRepository.save(saleDetail);
        });
    }

    @Override
    public List<SaleDetail> getAllsale() {
        List<SaleDetail>list = saleDetailRepository.findAll();
        return list;
    }

    @Override
    public Sale getById(Long saleId) {
        return saleRepository.findById(saleId).orElseThrow(() -> new ResourceNotFoundException("Sale ", saleId));
    }

    @Override
    public void cancelSale(Long saleId) {

        // update sell status
        Sale sale = getById(saleId);
        sale.setActive(false);
        saleRepository.save(sale);

        // get sale detail for update stock
        List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(saleId);
        List<Long> productId = saleDetails.stream().map(saleDetail -> saleDetail.getProduct().getId()).toList();
        List<Product> products = productRepository.findAllById(productId);
        Map<Long ,Product>productMap=products.stream().collect(Collectors.toMap(Product::getId,Function.identity()));
        saleDetails.forEach(saleDetail -> {
            Product product = productMap.get(saleDetail.getProduct().getId());
            product.setAvailableUnit(product.getAvailableUnit()+saleDetail.getUnit());
            productRepository.save(product);
        });
    }
}
