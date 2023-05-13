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

@RestController
@RequestMapping("/customers")
public class CustomerController {

    public static final String ID_NOT_FOUND = "ID NOT FOUND ";
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws Exception {
        List<CustomerDTO> customerDTOs = customerService.listAll()
            .stream()
            .map(customer -> mapper.map(customer, CustomerDTO.class))
            .toList();
        return ResponseEntity.status(HttpStatus.OK).body(customerDTOs);
    }

    @GetMapping("/{idCustomer}")
    public ResponseEntity<CustomerDTO> getCustomerById(
        @PathVariable("idCustomer") Integer idCustomer) throws Exception {
        Customer customer = customerService.listById(idCustomer);
        if (customer == null) {
            throw new ModelNotFoundException(ID_NOT_FOUND + idCustomer);
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(customer, CustomerDTO.class));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO)
        throws Exception {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Customer customer = mapper.map(customerDTO, Customer.class);
        Address address = mapper.map(customerDTO, Address.class);
        CustomerDTO obj = mapper.map(
            customerService.transactionalRecord(customer, address, "CREATED"), CustomerDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @PutMapping("/{idCustomer}")
    public ResponseEntity<CustomerDTO> updateCustomer(
        @PathVariable("idCustomer") Integer idCustomer, @RequestBody CustomerDTO customerDTO)
        throws Exception {
        Customer customer = customerService.listById(idCustomer);

        if (customer == null) {
            throw new ModelNotFoundException(ID_NOT_FOUND + customerDTO.getId());
        }

        customerDTO.setId(customer.getId());
        customerDTO.getAddress().setIdAdress(customer.getAddress().getIdAdress());
        Address address = customerDTO.getAddress();
        Customer newCustomer = mapper.map(customerDTO, Customer.class);
        CustomerDTO updatedCustomerDTO = mapper.map(
            customerService.transactionalRecord(newCustomer, address, "UPDATE"), CustomerDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomerDTO);
    }

    @DeleteMapping("/{idCustomer}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("idCustomer") Integer idCustomer)
        throws Exception {
        Customer customer = customerService.listById(idCustomer);
        if (customer == null) {
            throw new ModelNotFoundException(ID_NOT_FOUND + idCustomer);
        }
        customerService.delete(idCustomer);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
