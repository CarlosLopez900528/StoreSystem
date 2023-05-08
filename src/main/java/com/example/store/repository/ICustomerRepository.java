package com.example.store.repository;

import com.example.store.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT count(*) FROM tbl_customer where phone=:phone and email=:email", nativeQuery = true)
    Integer findByPhoneAndEmail(@Param("phone") String phone, @Param("email") String email);

}
