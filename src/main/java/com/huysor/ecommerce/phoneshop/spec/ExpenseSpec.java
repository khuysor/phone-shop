package com.huysor.ecommerce.phoneshop.spec;

import com.huysor.ecommerce.phoneshop.entity.ProductImportHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Data
public class ExpenseSpec implements Specification<ProductImportHistory> {
    private ExpenseFilter expenseFilter;

    @Override
    public Predicate toPredicate(Root<ProductImportHistory> importHistory, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicate = new ArrayList<>();
        if (Objects.nonNull(expenseFilter.getStartDate())) {
            Predicate startDate = cb.greaterThan(importHistory.get("dateImport"),expenseFilter.getStartDate());
            predicate.add(startDate);
        }
        if(Objects.nonNull(expenseFilter.getEndDate())){
            Predicate endDate= cb.lessThanOrEqualTo(importHistory.get("dateImport"),expenseFilter.getEndDate());
            predicate.add(endDate);
        }
        return cb.and(predicate.toArray(Predicate[]::new));
    }
}
