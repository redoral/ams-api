package com.revature.amsapi.service;

import com.revature.amsapi.entity.Customer;
import com.revature.amsapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(int customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("Customer with ID: " + customerId + " does not exist."));
    }

    public Customer createCustomer(Customer customer){ return customerRepository.save(customer); }

    public boolean deleteCustomer(int customerId) {
        customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("Customer with ID: " + customerId + " does not exist."));

        try {
            customerRepository.deleteById(customerId);
        } catch (Exception e) {
            System.out.println("Error:" + e);
            return false;
        }

        return true;
    }

    @Transactional
    public Customer updateCustomer(int customerId, Customer customer) {
        Customer updatedCustomer = customerRepository.findById(customerId).orElseThrow(() ->
                new IllegalStateException("Customer with ID: " + customerId + " does not exist."));

        if (customer.getName() !=  null) {
            updatedCustomer.setName(customer.getName());
        }

        return customerRepository.save(updatedCustomer);
    }
}
