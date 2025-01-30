package com.example.billingservice.feign;

import com.example.billingservice.models.Customer;
import com.example.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface ProductRestClient {

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable String id);
    @GetMapping("/products")
    List<Product> getAllProducts();

}
