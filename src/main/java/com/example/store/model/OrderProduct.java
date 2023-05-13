package com.example.store.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "orders_products")
@IdClass(OrderProductPK.class)
public class OrderProduct {
    @Id
    private Order order;
    @Id
    private Product product;

    private Long quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(
            o)) {
            return false;
        }
        OrderProduct that = (OrderProduct) o;
        return getOrder() != null && Objects.equals(getOrder(), that.getOrder())
            && getProduct() != null && Objects.equals(getProduct(), that.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
