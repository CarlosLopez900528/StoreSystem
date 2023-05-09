package com.example.store.repository;

import com.example.store.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
}
