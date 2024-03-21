package com.huysor.ecommerce.phoneshop.repository;


import com.huysor.ecommerce.phoneshop.entity.Sale;

import com.huysor.ecommerce.phoneshop.peojection.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    // raw query
    @Query(value = "select p.color_id as color, p.product_id as productId , p.product_name  as productName ,\n" +
            "sum(sd.unit) as unit,sum(sd.amount) amount\n" +
            "from sale_detail sd\n" +
            "inner join sale s on sd.sale_id = s.sale_id\n" +
            "inner join products p on p.product_id=sd.product_id\n" +
            "where date(s.sold_date)>= :startDate and date(s.sold_date)<= :endDate\n" +
            "group by p.product_id,p.product_name\r\n"
            +"",nativeQuery = true)

    List<ProductSold> findProductSold(LocalDate startDate, LocalDate endDate);
}
