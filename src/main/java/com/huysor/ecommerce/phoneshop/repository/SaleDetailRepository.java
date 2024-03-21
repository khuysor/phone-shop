package com.huysor.ecommerce.phoneshop.repository;

import com.huysor.ecommerce.phoneshop.dto.ProductReportDTO;
import com.huysor.ecommerce.phoneshop.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long>, JpaSpecificationExecutor<SaleDetail> {
  List<SaleDetail> findBySaleId(Long saleId);
}
