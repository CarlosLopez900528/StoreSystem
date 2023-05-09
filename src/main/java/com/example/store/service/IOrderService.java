package com.example.store.service;

import com.example.store.model.Order;
import com.example.store.model.Product;

import java.util.List;


public interface IOrderService extends ICRUD<Order, Integer>{
    Order saveOrder(Order order, List<Product> listProduct) throws Exception;

    Order getOrderById(Integer idOrder) throws Exception;

}
