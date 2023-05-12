package com.example.store.dto;


import lombok.Data;

import java.util.List;

@Data
public class OrderListProductDTO {
    private OrderDTO orderDTO;
    private List<ProductDTO> productDTOList;
}
