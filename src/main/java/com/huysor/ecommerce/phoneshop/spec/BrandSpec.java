package com.huysor.ecommerce.phoneshop.spec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.huysor.ecommerce.phoneshop.entity.Brands;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class BrandSpec implements Specification<Brands> {
	private final BrandFilter brandFilter;
	List<Predicate> predicates = new ArrayList<>();

	@Override
	public Predicate toPredicate(Root<Brands> brands, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (brandFilter.getName() != null) {
//			Predicate name = brands.get("name").in(brandFilter.getName()); this get from table where name =name

			Predicate name= cb.like(cb.lower(brands.get("name")),"%"+brandFilter.getName()+"%");
			predicates.add(name);
		}
		if (brandFilter.getId() != null) {
			Predicate id = brands.get("id").in(brandFilter.getId());
			predicates.add(id);
		}

//		return cb.and(predicates.toArray(new Predicate[0])); // this java 7 version style
		return cb.and(predicates.toArray(Predicate[]::new)); // this java 8 version style
	}

}
