package com.huysor.ecommerce.phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products",uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id","color_id"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id",unique = true)
    private Long id;
    @Column(name = "product_name",unique = true)
    private String name;
    @Column(name = "image_path ")
    private String imagePath;
    @Column(name = "available_unit")
    private Integer availableUnit;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
    @DecimalMin(value = "0.01",message = "Price must be greater than 0.01")
    @Column(name = "sale_price")
    private BigDecimal salePrice;


}
