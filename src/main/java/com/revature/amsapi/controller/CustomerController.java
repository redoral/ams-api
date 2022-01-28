package com.revature.amsapi.controller;

import com.revature.amsapi.entity.Customer;
import com.revature.amsapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customers")
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
    public Customer getCustomerById(@PathVariable("customerId") Integer customerId) {
        try {
            return customerService.getCustomer(customerId);
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){ return customerService.createCustomer(customer); }

    @DeleteMapping(path = "{customerId}")
    public boolean deleteCustomer(@PathVariable("customerId") Integer customerId){
        try {
            customerService.deleteCustomer(customerId);
            return true;
        } catch(IllegalStateException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @PutMapping(path = "{accountId}")
    public Customer updateCustomer(@PathVariable("customerId") Integer customerId, Customer customer){
        try {
            Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
            return updatedCustomer;
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
