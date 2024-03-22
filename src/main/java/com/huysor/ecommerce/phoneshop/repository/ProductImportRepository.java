package com.huysor.ecommerce.phoneshop.repository;

import com.huysor.ecommerce.phoneshop.entity.ProductImportHistory;
import com.huysor.ecommerce.phoneshop.spec.ExpenseFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImportRepository extends JpaRepository<ProductImportHistory,Long> , JpaSpecificationExecutor<ProductImportHistory> {
}
