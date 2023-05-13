package com.example.store.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductRequest {
    private Integer id;
    private String description;
    private BigDecimal price;
    private Long weight;
    private Long quantity;
}
