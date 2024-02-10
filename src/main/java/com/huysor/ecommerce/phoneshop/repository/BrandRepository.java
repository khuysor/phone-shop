package com.huysor.ecommerce.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huysor.ecommerce.phoneshop.entity.Brands;


public interface BrandRepository extends JpaRepository<Brands,Integer> {
}
