package com.example.store.service;


import com.example.store.model.Customer;
import com.example.store.model.Address;

public interface ICustomerService extends ICRUD<Customer, Integer> {

    Customer registrarTransactional(Customer customer, Address address) throws Exception;

}
