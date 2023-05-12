package com.example.store.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductDTO {
    private Integer id;
    private String description;
    private BigDecimal price;
    private String weight;
}
