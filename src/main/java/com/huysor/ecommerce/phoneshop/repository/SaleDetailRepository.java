package com.huysor.ecommerce.phoneshop.repository;

import com.huysor.ecommerce.phoneshop.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail,Long> {
  List<SaleDetail> findBySaleId(Long saleId);
}
