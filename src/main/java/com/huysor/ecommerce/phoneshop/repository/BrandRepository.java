package com.huysor.ecommerce.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huysor.ecommerce.phoneshop.entity.Brands;

public interface BrandRepository extends JpaRepository<Brands, Integer> {
//	 this is find by name but not care about upper case or lower case
//	List<Brands> findByNameIgnoreCase(String name);

//	 this find by name like 100%
//	List<Brands> findByNameLike(String name);
	
	List<Brands> findByNameContainingIgnoreCase(String name);
	
}
