package com.huysor.ecommerce.phoneshop.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.entity.ProductImportHistory;

public class ReportTestHelper {
    private static Product product = Product.builder()
            .id(1L)
            .name("Iphone 11 pro")
            .build();
    private static Product product2 = Product.builder()
            .id(2L)
            .name("Iphone 12 pro")
            .build();
    private static Product product3=Product.builder()
            .id(3L)
            .name("Iphone 13 pro")
            .build();
    public static List<Product> getProducts() {
        return List.of(product,product2,product3);
    }

    public static List<ProductImportHistory> getProductImportHistories(){
        ProductImportHistory pImportHistory = ProductImportHistory.builder()
                .product(product)
                .importUnit(10)
                .pricePerUnit(BigDecimal.valueOf(1000))
                .dateImport(LocalDateTime.of(2024, 03, 01, 10, 20, 34))
                .build();

        ProductImportHistory pImportHistory1 = ProductImportHistory.builder().product(product2)
                .importUnit(10)
                .pricePerUnit(BigDecimal.valueOf(1500))
                .dateImport(LocalDateTime.of(2024, 03, 02, 10, 20, 34))
                .build();

        ProductImportHistory pImportHistory2 = ProductImportHistory.builder().product(product3)
                .importUnit(10)
                .pricePerUnit(BigDecimal.valueOf(2000))
                .dateImport(LocalDateTime.of(2024, 03, 03, 10, 20, 34))
                .build();
        return List.of(pImportHistory,pImportHistory1,pImportHistory2);
    }

}
