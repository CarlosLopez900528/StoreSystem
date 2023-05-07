package com.example.store.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Integer idOrder;
    private String orderNumber;
    private LocalDateTime date;
    private Integer idCustomer;
    private Integer idShippingAddress;
    private String paymentType;
}
