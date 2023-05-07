package com.example.store.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Integer idProduct;
    private String description;
    private BigDecimal price;
    private String weight;
}
