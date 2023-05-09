package com.example.store.service.impl;

import com.example.store.exeption.ModelNotFoundException;
import com.example.store.model.Order;
import com.example.store.model.Product;
import com.example.store.repository.IOrderRepository;
import com.example.store.repository.OrderProductRepository;
import com.example.store.service.IOrderService;
import com.example.store.service.IProductService;
import com.example.store.utils.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl extends CRUDImpl<Order, Integer> implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    protected JpaRepository<Order, Integer> getRepository() {
        return orderRepository;
    }

    @Transactional
    @Override
    public Order saveOrder(Order order, List<Product> listProduct) throws Exception {
        BigDecimal totalOrderValue = BigDecimal.ZERO;
        var date = LocalDateTime.now();
        order.setPaymentType(PaymentType.CASH.getPayment());
        order.setOrderNumber(
                Stream.of(date.toString().split("[\\-T:]"))
                        .map(String::trim)
                        .collect(Collectors.joining())
        );
        order.setDate(date);
        orderRepository.save(order);
        for (int i = 0; i < listProduct.size(); i++) {

            Product p = productService.listById(listProduct.get(i).getIdProduct());
            orderProductRepository.saveOrderProduct(order.getIdOrder(), listProduct.get(i).getIdProduct());
            totalOrderValue = totalOrderValue.add(p.getPrice());
            List<Product> products = order.getListProducts();
            products.forEach(product -> {
                product.setDescription(p.getDescription());
                product.setPrice(p.getPrice());
                product.setWeight(p.getWeight());
            });
        }
        order.setTotalOrderValue(totalOrderValue);
        return order;
    }


    @Override
    public Order getOrderById(Integer idOrder) throws Exception {
        Order order = orderRepository.findById(idOrder).orElse(null);
        List<Product> products = listProductsByIdOrder(idOrder);
        order.setListProducts(products);
        return order;
    }

    private List<Product> listProductsByIdOrder(Integer idOrder) throws Exception {
        List<Integer> listIdProductByOrder = orderProductRepository.findIdProductByOrder(idOrder);
        return listIdProductByOrder.stream()
                .map(id -> {
                    try {
                        return productService.listById(id);
                    } catch (Exception e) {
                        throw new ModelNotFoundException("ID NOT FOUND " + idOrder);
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


}
