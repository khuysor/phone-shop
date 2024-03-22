package com.huysor.ecommerce.phoneshop.spec;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ExpenseFilter {
    private LocalDate startDate;
    private LocalDate endDate;
}
