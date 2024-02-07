package com.huysor.ecommerce.phoneshop.entity;

import jakarta.persistence.*;
import jdk.jfr.Relational;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name = "Model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brands brans;
}
