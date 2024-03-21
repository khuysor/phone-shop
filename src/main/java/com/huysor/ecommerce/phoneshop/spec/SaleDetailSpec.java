package com.huysor.ecommerce.phoneshop.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.huysor.ecommerce.phoneshop.entity.SaleDetail;
import com.huysor.ecommerce.phoneshop.entity.Sale;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleDetailSpec implements Specification<SaleDetail> {
    private SaleDetailFilter saleDetailFilter;

    @Override
    public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        // join from saledetail to sale;
        Join<SaleDetail, Sale> sale = saleDetail.join("sale");
        if (Objects.nonNull(saleDetailFilter.getStartDate())) {
 
         Predicate startDate=cb.greaterThan(sale.get("soldDate"), saleDetailFilter.getStartDate());
         predicates.add(startDate);
        }
        if (Objects.nonNull(saleDetailFilter.getEndDate())) {
          Predicate endDate=  cb.lessThan(sale.get("soldDate"), saleDetailFilter.getEndDate());
          predicates.add(endDate);
        
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }

}
