package com.example.store.model;

import jakarta.persistence.*;


@Entity
@Table(name = "tbl_order_product")
@IdClass(OrderProductPK.class)
public class OrderProduct {
    @Id
    private Order order;
    @Id
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
