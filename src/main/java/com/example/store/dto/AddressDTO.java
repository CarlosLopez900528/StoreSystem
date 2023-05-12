package com.example.store.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private Integer id;
    private String address;
    private String country;
    private String city;
    private String state;
    private String zipCode;

}
