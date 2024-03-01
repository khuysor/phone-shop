package com.huysor.ecommerce.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.huysor.ecommerce.phoneshop.entity.Brands;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brands, Long>, JpaSpecificationExecutor<Brands> {
//	 this is find by name but not care about upper case or lower case
	List<Brands> findByNameIgnoreCase(String name);

//	 this find by name where name =name
	List<Brands> findByNameLike(String name);

//	this is find by name like 
	List<Brands> findByNameContainingIgnoreCase(String name);

}
