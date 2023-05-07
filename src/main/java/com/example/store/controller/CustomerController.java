package com.example.store.controller;

import com.example.store.model.Customer;
import com.example.store.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public List<Customer> getAllCustomers() throws Exception {
        return customerService.listAllCustomers();
    }

    @GetMapping("/{idCustomer}")
    public Customer getCustomerById(@PathVariable("idCustomer") Integer idCustomer) throws Exception {
        return customerService.listCustomersById(idCustomer);
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) throws Exception {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/{idCustomer}")
    public Customer updateCustomer(@PathVariable("idCustomer") Integer idCustomer, @RequestBody Customer customer) throws Exception {
        Customer c = customerService.listCustomersById(idCustomer);
        if (c != null) {
            customer.setIdCustomer(idCustomer);
            return customerService.updateCustomer(customer);
        }
        return null;
    }

    @DeleteMapping("/{idCustomer}")
    public void deleteCustomerById(@PathVariable("idCustomer") Integer idCustomer) throws Exception {
        customerService.deleteCustomer(idCustomer);
    }
}
