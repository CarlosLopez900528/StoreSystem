package com.example.store.controller;

import com.example.store.dto.OrderDTO;
import com.example.store.exeption.ModelNotFoundException;
import com.example.store.model.Order;
import com.example.store.model.Product;
import com.example.store.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<OrderDTO>> getAllOrders() throws Exception {
        return new ResponseEntity<>(orderService.listAll()
                .stream()
                .map(o -> mapper.map(o, OrderDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("idOrder") Integer idOrder) throws Exception {
        Order order = orderService.getOrderById(idOrder);
        if (order == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + idOrder);
        }
        return new ResponseEntity<>(mapper.map(order, OrderDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> saveCustomer(@RequestBody OrderDTO orderDTO) throws Exception {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Order order = mapper.map(orderDTO, Order.class);
        List<Product> listProduct = mapper.map(orderDTO.getListProducts(), new TypeToken<List<Product>>() {}.getType());
        Order newOrder = orderService.saveOrder(order, listProduct);
        OrderDTO obj = mapper.map(newOrder, OrderDTO.class);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }
}
