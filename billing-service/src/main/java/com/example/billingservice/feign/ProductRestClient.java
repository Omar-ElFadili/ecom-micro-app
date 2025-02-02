package com.example.billingservice.feign;

import com.example.billingservice.models.Customer;
import com.example.billingservice.models.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "inventory-service")
public interface ProductRestClient {

    @GetMapping("/products/{id}")
    @CircuitBreaker(name = "inventoryServiceCB", fallbackMethod = "getDefaultProduct")
    Product getProductById(@PathVariable String id);

    @GetMapping("/products")
    @CircuitBreaker(name = "inventoryServiceCB", fallbackMethod = "getDefaultProducts")
    List<Product> getAllProducts();

    default Product getDefaultProduct(String id, Exception exception) {
        Product product = Product.builder()
                .id(id)
                .name("default name")
                .price(0)
                .quantity(0)
                .build();
        return product;
    }

    default List<Product> getDefaultProducts(Exception exception) {
        return new ArrayList<>();
    }
}
