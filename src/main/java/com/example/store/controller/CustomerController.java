package com.example.store.controller;

import com.example.store.dto.CustomerDTO;
import com.example.store.exeption.ModelNotFoundException;
import com.example.store.model.Customer;
import com.example.store.model.Address;
import com.example.store.service.ICustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws Exception {
        return new ResponseEntity<>(customerService.listAll()
                .stream()
                .map(p -> mapper.map(p, CustomerDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{idCustomer}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("idCustomer") Integer idCustomer) throws Exception {
        Customer customer = customerService.listById(idCustomer);
        if (customer == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + idCustomer);
        }
        return new ResponseEntity<>(mapper.map(customer, CustomerDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Customer customer = mapper.map(customerDTO, Customer.class);
        Address address = mapper.map(customerDTO, Address.class);
        CustomerDTO obj = mapper.map(customerService.transactionalRecord(customer, address, "CREATED"), CustomerDTO.class);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping("/{idCustomer}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("idCustomer") Integer idCustomer, @RequestBody CustomerDTO customerDTO) throws Exception {
        Customer customer = customerService.listById(idCustomer);

        if (customer == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + customerDTO.getId());
        }

        customerDTO.setId(customer.getId());
        customerDTO.getAddress().setId(customer.getAddress().getId());
        Address address = customerDTO.getAddress();
        Customer newCustomer = mapper.map((customerDTO),Customer.class);
        CustomerDTO obj = mapper.map(customerService.transactionalRecord(newCustomer, address, "UPDATE"), CustomerDTO.class);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{idCustomer}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("idCustomer") Integer idCustomer) throws Exception {
        Customer customer = customerService.listById(idCustomer);
        if (customer == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + idCustomer);
        }
        customerService.delete(idCustomer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
