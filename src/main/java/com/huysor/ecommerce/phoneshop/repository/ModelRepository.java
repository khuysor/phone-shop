package com.huysor.ecommerce.phoneshop.repository;

import com.huysor.ecommerce.phoneshop.entity.Brands;
import com.huysor.ecommerce.phoneshop.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {

}
