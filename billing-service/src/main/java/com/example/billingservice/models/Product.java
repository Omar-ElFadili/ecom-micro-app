package com.example.billingservice.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class Product {
    private String id;
    private String name;
    private int quantity;
    private double price;


}
