package com.example.store.service.impl;

import com.example.store.dto.OrderDTO;
import com.example.store.dto.ProductRequest;
import com.example.store.exeption.ModelNotFoundException;
import com.example.store.model.Order;
import com.example.store.model.OrderProduct;
import com.example.store.model.Product;
import com.example.store.repository.IOrderRepository;
import com.example.store.repository.OrderProductRepository;
import com.example.store.service.IOrderService;
import com.example.store.service.IProductService;
import com.example.store.utils.PaymentType;

import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl extends CRUDImpl<Order, Integer> implements IOrderService {

    public static final String YYYY_M_MDD_H_HMMSS = "yyyyMMddHHmmss";
    public static final String ID_NOT_FOUND = "ID NOT FOUND: ";
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    protected JpaRepository<Order, Integer> getRepository() {
        return orderRepository;
    }

    @Transactional
    @Override
    public Order saveOrder(OrderDTO orderDTO) throws Exception {
        Order order = mapper.map(orderDTO, Order.class);
        BigDecimal totalOrderValue = BigDecimal.ZERO;
        LocalDateTime date = LocalDateTime.now();
        order.setPaymentType(PaymentType.CASH.getPayment());
        order.setOrderNumber(date.format(DateTimeFormatter.ofPattern(YYYY_M_MDD_H_HMMSS)));
        order.setDate(date);
        orderRepository.save(order);

        List<ProductRequest> productRequests = orderDTO.getListProducts();
        for (ProductRequest pdt : productRequests) {
            Product p = productService.listById(pdt.getId());
            orderProductRepository.saveOrderProduct(order.getId(), pdt.getId(), pdt.getQuantity());
            totalOrderValue = p.getPrice().multiply(BigDecimal.valueOf(pdt.getQuantity()))
                    .add(totalOrderValue);
            pdt.setDescription(p.getDescription());
            pdt.setPrice(p.getPrice());
            pdt.setWeight(p.getWeight());
        }
        order.setListProducts(mapper.map(productRequests, new TypeToken<List<ProductRequest>>() {
        }.getType()));
        order.setTotalOrderValue(totalOrderValue);
        return order;
    }


    @Override
    public Order getOrderById(Integer id) throws ModelNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(ID_NOT_FOUND + id));

        List<Product> products = listProductsByIdOrder(id);
        order.setListProducts(products);
        return order;
    }

    @Override
    public List<Order> getAllOrders() throws Exception {
        List<ProductRequest> listProduct = new ArrayList<>();
        List<Order> listOrder = orderRepository.findAll();
        for (Order order : listOrder) {
            List<OrderProduct> listOrderProduct = orderProductRepository.findOrderProductByIdOrder(order.getId());
            for (OrderProduct orderProduct : listOrderProduct) {
                ProductRequest productRequest = mapper.map(productService.listById(orderProduct.getProduct().getId()), ProductRequest.class);
                productRequest.setQuantity(orderProduct.getQuantity());
                listProduct.add(productRequest);
            }
            order.setListProducts(mapper.map(listProduct, new TypeToken<List<ProductRequest>>() {
            }.getType()));
        }
        return listOrder;
    }

    private List<Product> listProductsByIdOrder(Integer idOrder) throws ModelNotFoundException {
        List<Integer> listIdProductByOrder = orderProductRepository.findIdProductByOrder(idOrder);
        return listIdProductByOrder.stream()
                .map(id -> {
                    try {
                        return productService.listById(id);
                    } catch (Exception e) {
                        throw new ModelNotFoundException(ID_NOT_FOUND + id);
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }
}
