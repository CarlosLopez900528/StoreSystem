package com.example.store.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders_products")
@IdClass(OrderProductPK.class)
public class OrderProduct {
    @Id
    private Order order;
    @Id
    private Product product;
}
