package com.example.billingservice.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class Customer {
    private Long id;
    private String name;
    private String email;

}
