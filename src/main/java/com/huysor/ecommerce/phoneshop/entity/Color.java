package com.huysor.ecommerce.phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private long id;
    @Column(name = "color_name")
    private String name;
}
