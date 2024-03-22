package com.huysor.ecommerce.phoneshop.controller;

import com.huysor.ecommerce.phoneshop.dto.ExpenseReportDTO;
import com.huysor.ecommerce.phoneshop.dto.ProductDTO;
import com.huysor.ecommerce.phoneshop.dto.ProductReportDTO;
import com.huysor.ecommerce.phoneshop.entity.SaleDetail;
import com.huysor.ecommerce.phoneshop.peojection.ProductSold;
import com.huysor.ecommerce.phoneshop.services.ReprotService;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("reports")
public class ReportController {
    private final ReprotService reprotService;

    // @GetMapping("{startDate}/{endDate}")
    // public ResponseEntity<?> productSold(@DateTimeFormat(pattern = "yyyy-MM-dd")
    // @PathVariable("startDate") LocalDate startDate, @DateTimeFormat(pattern =
    // "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {

    // List<ProductSold> productSold = reprotService.getAllProductSold(startDate,
    // endDate);

    // return ResponseEntity.ok(productSold);
    // }

    @GetMapping("{startDate}/{endDate}")
    public ResponseEntity<?> productSold(
            @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {

        List<ProductReportDTO> productReportDTOs = reprotService.getProductReport(startDate, endDate);
        return ResponseEntity.ok(productReportDTOs);
    }

    @GetMapping("expense/{startDate}/{endDate}")
    public ResponseEntity<?> expenseReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {
        List<ExpenseReportDTO> list = reprotService.getExpenseReport(startDate, endDate);
        return ResponseEntity.ok(list);
    }
}

