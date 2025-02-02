package com.example.billingservice.feign;

import com.example.billingservice.models.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerServiceCB", fallbackMethod = "getDefaultCustomer")
    Customer getCustomerById(@PathVariable Long  id);
    @GetMapping("/customers")
    @CircuitBreaker(name = "customerServiceCB", fallbackMethod = "getDefaultCustomers")
    List<Customer> getAllCustomers();

    default Customer getDefaultCustomer(Long id, Exception exception) {
        Customer customer = Customer.builder()
                .id(id)
                .email("default email")
                .name("default name")
                .build();
        return customer;
    }
    default List<Customer> getDefaultCustomers(Exception exception) {
        return new ArrayList<>();
    }
}
