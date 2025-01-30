package com.example.customerservice.controllers;

import com.example.customerservice.config.CustomerConfigParams;
import com.example.customerservice.entities.Customer;
import com.example.customerservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@RefreshScope
public class CustomerController {

    @Value("${global.params.p1}")
    private String p1;
    @Value("${global.params.p2}")
    private String p2;

    private final CustomerConfigParams customerConfigParams;
    final CustomerService customerService;
    public CustomerController(CustomerService customerService, CustomerConfigParams customerConfigParams) {
        this.customerService = customerService;
        this.customerConfigParams = customerConfigParams;
    }

//    @PostMapping
//    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
////        for (Command command : customer.getCommands()) {
////            command.setCustomer(customer);
////        }
//        customerService.addCustomer(customer);
//        return new ResponseEntity<>(customer, HttpStatus.CREATED);
//    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
//        if (customer.isPresent()) {
//            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, Long customerId) {
        customerService.updateCustomer(customer, customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestParam Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("customer was deleted", HttpStatus.OK);
    }
    @GetMapping("/config")
    public Map<String, String> getCustomerConfig() {
        return Map.of("p1", p1, "p2", p2);
    }

    @GetMapping("/config2")
    public CustomerConfigParams getCustomerDevConfig() {
        return customerConfigParams;
    }

}
