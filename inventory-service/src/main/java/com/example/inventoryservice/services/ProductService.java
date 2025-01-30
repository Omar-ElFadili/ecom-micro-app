package com.example.inventoryservice.services;

import com.example.inventoryservice.entities.Product;
import com.example.inventoryservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository inventoryRepository;
    public ProductService(ProductRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    public List<Product> getAllInventory() {
        return inventoryRepository.findAll();
    }
    public Product getInventoryById(String id) {
        return inventoryRepository.getById(id);
    }
}
