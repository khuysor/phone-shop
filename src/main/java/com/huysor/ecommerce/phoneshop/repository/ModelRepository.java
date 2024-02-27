package com.huysor.ecommerce.phoneshop.repository;

import com.huysor.ecommerce.phoneshop.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Integer> {
List<Model>findByBrandsId(Integer brandsId);
}
