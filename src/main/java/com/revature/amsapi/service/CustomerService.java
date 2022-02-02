package com.revature.amsapi.service;

import com.revature.amsapi.entity.Customer;
import com.revature.amsapi.exception.CustomerNotFoundException;
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

    public Customer getCustomer(int customerId) throws CustomerNotFoundException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer with ID: " + customerId + " does not exist."));
    }

    public Customer createCustomer(Customer customer){ return customerRepository.save(customer); }

    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException{
        customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer with ID: " + customerId + " does not exist."));
        customerRepository.deleteById(customerId);
        return true;
    }

    @Transactional
    public Customer updateCustomer(int customerId, Customer customer) throws CustomerNotFoundException {
        Customer updatedCustomer = customerRepository.findById(customerId).orElseThrow(() ->
                new CustomerNotFoundException("Customer with ID: " + customerId + " does not exist."));

        if (customer.getName() != null) {
            updatedCustomer.setName(customer.getName());
        }

        if (customer.getAddress() != null) {
            updatedCustomer.setAddress(customer.getAddress());
        }

        if (customer.getEmail() != null) {
            updatedCustomer.setEmail(customer.getEmail());
        }

        return customerRepository.save(updatedCustomer);
    }
}
