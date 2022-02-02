package com.revature.amsapi.controller;

import com.revature.amsapi.entity.Customer;
import com.revature.amsapi.exception.CustomerNotFoundException;
import com.revature.amsapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customers")
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping(path = "{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Integer customerId) throws CustomerNotFoundException { return customerService.getCustomer(customerId); }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){ return customerService.createCustomer(customer); }

    @DeleteMapping(path = "{customerId}")
    public boolean deleteCustomer(@PathVariable("customerId") Integer customerId) throws CustomerNotFoundException { return customerService.deleteCustomer(customerId); }

    @PutMapping(path = "{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") Integer customerId, Customer customer) throws CustomerNotFoundException { return customerService.updateCustomer(customerId, customer); }
}
