package com.example.store.service;

import com.example.store.model.Customer;

import java.util.List;

public interface ICustomerService {
    Customer saveCustomer(Customer customer) throws Exception;
    Customer updateCustomer(Customer customer) throws Exception;
    List<Customer> listAllCustomers() throws Exception;
    Customer listCustomersById(Integer idCustomer) throws Exception;
    void deleteCustomer(Integer idCustomer) throws Exception;
}
