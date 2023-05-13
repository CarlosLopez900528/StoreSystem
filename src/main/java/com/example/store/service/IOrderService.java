package com.example.store.service;

import com.example.store.dto.OrderDTO;
import com.example.store.dto.ProductRequest;
import com.example.store.model.Order;

import java.util.List;


public interface IOrderService extends ICRUD<Order, Integer>{
    Order saveOrder(OrderDTO order) throws Exception;

    Order getOrderById(Integer idOrder) throws Exception;

    List<Order> getAllOrders() throws Exception;

}
