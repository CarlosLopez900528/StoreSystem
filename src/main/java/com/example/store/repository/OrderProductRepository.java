package com.example.store.repository;

import com.example.store.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

    @Modifying
    @Query(value = "INSERT INTO tbl_order_product(id_order, id_product) VALUES (:idOrder, :idProduct)", nativeQuery = true)
    Integer saveOrderProduct(@Param("idOrder") Integer idOrder, @Param("idProduct") Integer idProduct);

    @Query(value = "SELECT id_product FROM tbl_order_product where id_order=:idOrder", nativeQuery = true)
    List<Integer> findIdProductByOrder(@Param("idOrder") Integer idOrder);
}
