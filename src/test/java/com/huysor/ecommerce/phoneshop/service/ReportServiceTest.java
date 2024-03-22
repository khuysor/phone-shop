package com.huysor.ecommerce.phoneshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import com.huysor.ecommerce.phoneshop.servicesImplement.ReportImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.huysor.ecommerce.phoneshop.dto.ExpenseReportDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.entity.ProductImportHistory;
import com.huysor.ecommerce.phoneshop.repository.ProductImportRepository;
import com.huysor.ecommerce.phoneshop.repository.ProductRepository;
import com.huysor.ecommerce.phoneshop.repository.SaleDetailRepository;
import com.huysor.ecommerce.phoneshop.repository.SaleRepository;
import com.huysor.ecommerce.phoneshop.services.ReportService;
import com.huysor.ecommerce.phoneshop.spec.ExpenseSpec;
import com.huysor.ecommerce.phoneshop.spec.SaleDetailSpec;

import lombok.RequiredArgsConstructor;

@ExtendWith(MockitoExtension.class)

public class ReportServiceTest {
    @Mock
    private SaleRepository saleRepository;
    @Mock
    private SaleDetailRepository saleDetailRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductImportRepository productImportRepository;

    private ReportService reportService;
    private List<ExpenseReportDTO> expenseReport;

    @BeforeEach
    public void setup() {
        reportService = new ReportImplement(saleRepository, saleDetailRepository, productRepository,
                productImportRepository);
    }

    @Test
    public void getExpenseReportTest() {

        // given
        List<ProductImportHistory> productImportHistories = ReportTestHelper.getProductImportHistories();

        List<Product> products = ReportTestHelper.getProducts();
        // when

        when(productImportRepository.findAll(Mockito.any(ExpenseSpec.class))).thenReturn(productImportHistories);

        when(productRepository.findAllById(anySet())).thenReturn(products);

        // then


        List<ExpenseReportDTO> expen = reportService.getExpenseReport(LocalDate.now().minusMonths(1),LocalDate.now());



        assertEquals(3, expen.size());
        ExpenseReportDTO expense1 = expen.get(0);
        assertEquals(1, expense1.getProductId());
        assertEquals("Iphone 11 pro", expense1.getProductName());
        assertEquals(10, expense1.getTotalUnit());
        assertEquals(10000d, expense1.getTotalAmount().doubleValue());



    }
}
