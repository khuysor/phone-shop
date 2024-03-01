package com.huysor.ecommerce.phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sale_detail")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_detail_id")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "sale_id")
    private Sale sale;
    @Column(name = "sold_date")
    private LocalDateTime soldDate;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "amount")
    private BigDecimal price;
    @Column(name = "unit")
    private Integer unit;

}
