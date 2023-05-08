package com.example.store.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_shipping_address")
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idShippingAddress;
    private String address;
    private String country;
    private String city;
    private String state;

    public Integer getIdShippingAddress() {
        return idShippingAddress;
    }

    public void setIdShippingAddress(Integer idShippingAddress) {
        this.idShippingAddress = idShippingAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
