package com.example.store.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShippingAddress {
    private Integer idShippingAddress;
    private String address;
    private String country;
    private String city;
    private String state;
    private String zipCode;
}
