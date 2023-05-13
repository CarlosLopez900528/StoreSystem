package com.example.store.controller;

import com.example.store.dto.OrderDTO;
import com.example.store.exeption.ModelNotFoundException;
import com.example.store.model.Order;
import com.example.store.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<OrderDTO>> getAllOrders() throws Exception {
        List<OrderDTO> orderDTOs = orderService.getAllOrders().stream()
            .map(o -> mapper.map(o, OrderDTO.class))
            .toList();
        return ResponseEntity.status(HttpStatus.OK).body(orderDTOs);
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("idOrder") Integer idOrder)
        throws Exception {
        Order order = orderService.getOrderById(idOrder);
        if (order == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + idOrder);
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(order, OrderDTO.class));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        Order newOrder = orderService.saveOrder(orderDTO);
        OrderDTO obj = mapper.map(newOrder, OrderDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
}
