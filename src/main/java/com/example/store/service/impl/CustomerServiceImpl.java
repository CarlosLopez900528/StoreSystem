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
    public Customer registrarTransactional(Customer customer, Address address) throws Exception {
        int count = customerRepository.findByPhoneAndEmail(customer.getPhone(),customer.getEmail());
        if (count>0){
            throw new ModelNotFoundException("THE CUSTOMER IS ALREADY REGISTERED !!!");
        }
        Address s = addressRepository.save(address);
        customer.setAddress(s);
        return customerRepository.save(customer);
    }
}
