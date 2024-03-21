package com.huysor.ecommerce.phoneshop.servicesImplement;

import com.huysor.ecommerce.phoneshop.dto.ProductDTO;
import com.huysor.ecommerce.phoneshop.dto.ProductReportDTO;
import com.huysor.ecommerce.phoneshop.entity.Product;
import com.huysor.ecommerce.phoneshop.entity.SaleDetail;
import com.huysor.ecommerce.phoneshop.peojection.ProductSold;
import com.huysor.ecommerce.phoneshop.repository.ProductRepository;
import com.huysor.ecommerce.phoneshop.repository.SaleDetailRepository;
import com.huysor.ecommerce.phoneshop.repository.SaleRepository;
import com.huysor.ecommerce.phoneshop.services.ReprotService;
import com.huysor.ecommerce.phoneshop.spec.SaleDetailFilter;
import com.huysor.ecommerce.phoneshop.spec.SaleDetailSpec;

import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportImplement implements ReprotService {
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;

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

            BigDecimal totalAmount=sdList.stream().map(SaleDetail::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

            ProductReportDTO reportDTO = new ProductReportDTO();
            reportDTO.setId(product.getId());
            reportDTO.setProductName(product.getName());
            reportDTO.setUnit(unit);
            reportDTO.setAmount(totalAmount);
            list.add(reportDTO);
        }

        return list;
    }

}
