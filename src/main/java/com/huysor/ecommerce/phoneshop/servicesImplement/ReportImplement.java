package com.huysor.ecommerce.phoneshop.servicesImplement;

import com.huysor.ecommerce.phoneshop.dto.ExpenseReportDTO;
import com.huysor.ecommerce.phoneshop.dto.ProductReportDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.entity.ProductImportHistory;
import com.huysor.ecommerce.phoneshop.entity.SaleDetail;
import com.huysor.ecommerce.phoneshop.peojection.ProductSold;
import com.huysor.ecommerce.phoneshop.repository.ProductImportRepository;
import com.huysor.ecommerce.phoneshop.repository.ProductRepository;
import com.huysor.ecommerce.phoneshop.repository.SaleDetailRepository;
import com.huysor.ecommerce.phoneshop.repository.SaleRepository;
import com.huysor.ecommerce.phoneshop.services.ReportService;
import com.huysor.ecommerce.phoneshop.spec.*;

import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportImplement implements ReportService {
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;
    private final ProductImportRepository productImportRepository;

    @Override
    public List<ProductSold> getAllProductSold(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate, endDate);

    }

    @Override
    public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
        List<ProductReportDTO> list = new ArrayList<>();

        SaleDetailFilter detailFilter = new SaleDetailFilter();
        detailFilter.setStartDate(startDate);
        detailFilter.setEndDate(endDate);
        Specification<SaleDetail> spec = new SaleDetailSpec(detailFilter);
        List<SaleDetail> saleDetails = saleDetailRepository.findAll(spec);

        List<Long> productIds = saleDetails.stream()
                .map(sd -> sd.getProduct().getId())
                .toList();
        Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream()
                .collect(Collectors.groupingBy(SaleDetail::getProduct));

        for (var entry : saleDetailMap.entrySet()) {
            Product product = productMap.get(entry.getKey().getId());
            List<SaleDetail> sdList = entry.getValue();

            // total unit
            Integer unit = sdList.stream().map(SaleDetail::getUnit)
                    .reduce(0, (a, b) -> a + b);
            /*
             * Integer integer = sdList.stream().map(SaleDetail::getUnit)
             * .reduce((a,b) -> a+b)
             * .get();
             */
            /*
             * Double totalAmount = sdList.stream()
             * .map(sd -> sd.getUnit() * sd.getAmount().doubleValue())
             * .reduce(0d, (a,b) -> a+b);
             */
            //      sum unit with price
            //     double totalAmount = sdList.stream()
            //             .mapToDouble(sd -> sd.getUnit() * sd.getPrice().doubleValue())
            //             .sum();

            BigDecimal totalAmount = sdList.stream().map(SaleDetail::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

            ProductReportDTO reportDTO = new ProductReportDTO();
            reportDTO.setId(product.getId());
            reportDTO.setProductName(product.getName());
            reportDTO.setUnit(unit);
            reportDTO.setAmount(totalAmount);
            list.add(reportDTO);
        }

        return list;
    }

    @Override
    public List<ExpenseReportDTO> getExpenseReport(LocalDate starDate, LocalDate endDate) {

        List<ExpenseReportDTO> expenseReportDTOS = new ArrayList<>();
        ExpenseFilter expenseFilter = new ExpenseFilter();
        expenseFilter.setStartDate(starDate);
        expenseFilter.setEndDate(endDate);
        Specification<ProductImportHistory> expenseSpec= new ExpenseSpec(expenseFilter);
        List<ProductImportHistory> productImportHistories = productImportRepository.findAll(expenseSpec);
        Set<Long> productId = productImportHistories.stream().map(productImportHistory -> productImportHistory.getProduct().getId()).collect(Collectors.toSet());
        Map<Long, Product> productMap = productRepository.findAllById(productId).stream().collect(Collectors.toMap(Product::getId, Function.identity()));
        Map<Product, List<ProductImportHistory>> productListMap = productImportHistories.stream()
                .collect(Collectors.groupingBy(pi -> pi.getProduct()));

        for (var entry : productListMap.entrySet()) {
            Product product = productMap.get(entry.getKey().getId());
            ExpenseReportDTO expenseReportDTO = new ExpenseReportDTO();
            List<ProductImportHistory> productImportHistoryList = entry.getValue();
            expenseReportDTO.setProductId(product.getId());
            expenseReportDTO.setProductName(product.getName());
            Double totalAmount = productImportHistoryList.stream().mapToDouble(pi -> pi.getImportUnit() * pi.getPricePerUnit().doubleValue()).sum();
//            BigDecimal totalAmount = productImportHistoryList.stream()
//                    .map(pi -> BigDecimal.valueOf(pi.getImportUnit()).multiply(pi.getPricePerUnit()))
//                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            expenseReportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
            Integer unit = productImportHistoryList.stream().mapToInt(pi -> pi.getImportUnit()).sum();
            expenseReportDTO.setTotalUnit(unit);
            expenseReportDTOS.add(expenseReportDTO);
        }

        // sort list by product id
        Collections.sort(expenseReportDTOS,(a,b)->(int)(a.getProductId()- b.getProductId()) );
        return expenseReportDTOS;
    }
}
