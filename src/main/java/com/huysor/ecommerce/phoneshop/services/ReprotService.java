package com.huysor.ecommerce.phoneshop.services;

import com.huysor.ecommerce.phoneshop.dto.ProductReportDTO;
import com.huysor.ecommerce.phoneshop.peojection.ProductSold;

import java.time.LocalDate;
import java.util.List;

public interface ReprotService {
List<ProductSold> getAllProductSold(LocalDate startDate,LocalDate endDate);
List<ProductReportDTO>getProductReport(LocalDate starDate,LocalDate endDate);
}
