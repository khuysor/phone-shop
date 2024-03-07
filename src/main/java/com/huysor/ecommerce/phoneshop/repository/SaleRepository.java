package com.huysor.ecommerce.phoneshop.repository;


import com.huysor.ecommerce.phoneshop.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
