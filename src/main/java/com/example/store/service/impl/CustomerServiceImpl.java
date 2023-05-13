package com.example.store.service.impl;

import com.example.store.exeption.ModelNotFoundException;
import com.example.store.model.Customer;
import com.example.store.model.Address;
import com.example.store.repository.ICustomerRepository;
import com.example.store.repository.IAddressRepository;
import com.example.store.service.ICustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends CRUDImpl<Customer, Integer> implements ICustomerService {

    public static final String THE_CUSTOMER_IS_ALREADY_REGISTERED = "THE CUSTOMER IS ALREADY REGISTERED!!!";
    public static final String CREATED = "CREATED";
    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Override
    protected JpaRepository<Customer, Integer> getRepository() {
        return customerRepository;
    }

    @Transactional
    @Override
    public Customer transactionalRecord(Customer customer, Address address, String type) throws Exception {
        int count = customerRepository.findByPhoneAndEmail(customer.getPhone(), customer.getEmail());
        if (count > 0 && CREATED.equals(type)) {
            throw new ModelNotFoundException(THE_CUSTOMER_IS_ALREADY_REGISTERED);
        }
        Address savedAddress = addressRepository.save(address);
        customer.setAddress(savedAddress);
        return customerRepository.save(customer);
    }
}
