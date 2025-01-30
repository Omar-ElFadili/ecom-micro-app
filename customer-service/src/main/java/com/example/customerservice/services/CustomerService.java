package com.example.customerservice.services;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(Long.valueOf(id));
    }
    public Customer addCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }
    public void updateCustomer(Customer customer, Long id) {
        Customer customerToUpdate = customerRepository.findById(id).orElse(null);
        customerToUpdate.setName(customer.getName());
        customerRepository.save(customerToUpdate);
    }
    public void deleteCustomer(Long id) {
        Customer customerToDelete = customerRepository.findById(id).orElse(null);
        assert customerToDelete != null;
        customerRepository.delete(customerToDelete);
    }
}
