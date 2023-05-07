package com.example.store.service;

import com.example.store.model.Customer;
import com.example.store.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) throws Exception {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) throws Exception {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> listAllCustomers() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public Customer listCustomersById(Integer idCustomer) throws Exception {
        Optional<Customer> customer = customerRepository.findById(idCustomer);
        return customer.isPresent() ? customer.get() : new Customer();
    }

    @Override
    public void deleteCustomer(Integer idCustomer) throws Exception {
        customerRepository.deleteById(idCustomer);
    }
}
