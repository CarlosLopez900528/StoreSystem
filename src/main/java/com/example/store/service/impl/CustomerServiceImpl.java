package com.example.store.service.impl;

import com.example.store.model.Customer;
import com.example.store.repository.ICustomerRepository;
import com.example.store.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends CRUDImpl<Customer, Integer> implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    protected JpaRepository<Customer, Integer> getRepository() {
        return customerRepository;
    }
}
