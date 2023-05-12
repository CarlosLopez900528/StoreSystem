package com.example.store.dto;

import com.example.store.model.Address;
import lombok.Data;

import java.util.Objects;

@Data
public class CustomerDTO {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(phone, that.phone) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email);
    }
}
