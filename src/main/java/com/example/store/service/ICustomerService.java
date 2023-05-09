package com.example.store.service;


import com.example.store.model.Customer;
import com.example.store.model.Address;

public interface ICustomerService extends ICRUD<Customer, Integer> {

    Customer transactionalRecord(Customer customer, Address address, String type) throws Exception;

}
