package com.example.inventoryservice.controllers;

import com.example.inventoryservice.entities.Product;
import com.example.inventoryservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService inventoryService;
    public ProductController(ProductService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = inventoryService.getAllInventory();
        for (Product p : products) {
            System.out.println(p.getName());
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable String id) {
//        Product product = inventoryService.getInventoryById(id);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }
}
