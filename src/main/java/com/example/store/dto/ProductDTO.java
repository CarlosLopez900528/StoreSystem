package com.example.store.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;
    private String description;
    private BigDecimal price;
    private String weight;

}
