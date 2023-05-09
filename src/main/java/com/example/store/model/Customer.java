package com.example.store.model;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "tbl_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCustomer;
    private String name;
    private String phone;
    private String email;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "idAddress", nullable = false, foreignKey = @ForeignKey(name = "FK_customer_address"))
    private Address address;

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(phone, customer.phone) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email);
    }
}
