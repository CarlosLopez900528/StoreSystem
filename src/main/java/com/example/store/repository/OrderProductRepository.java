package com.example.store.repository;

import com.example.store.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

    @Modifying
    @Query(value = "INSERT INTO orders_products(id_order, id_product, quantity) VALUES (:idOrder, :idProduct, :quantity)", nativeQuery = true)
    Integer saveOrderProduct(@Param("idOrder") Integer idOrder, @Param("idProduct") Integer idProduct, @Param("quantity") Long quantity);

    @Query(value = "SELECT id_product FROM orders_products where id_order=:idOrder", nativeQuery = true)
    List<Integer> findIdProductByOrder(@Param("idOrder") Integer idOrder);
}
