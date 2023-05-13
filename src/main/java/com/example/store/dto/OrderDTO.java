package com.example.store.dto;

import com.example.store.model.Customer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Integer id;
    private String orderNumber;
    private LocalDateTime date;
    private Customer customer;
    private String paymentType;
    private List<ProductRequest> listProducts;
    private BigDecimal totalOrderValue;
}
