package com.example.store.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderNumber;
    private LocalDateTime date;
    @OneToOne
    @JoinColumn(name = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_orders_customers"))
    private Customer customer;
    private String paymentType;
    @OneToMany(mappedBy = "id")
    private List<Product> listProducts;
    private BigDecimal totalOrderValue;
}
