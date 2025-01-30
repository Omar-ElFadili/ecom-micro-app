package com.example.customerservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties(prefix = "customer.params")
public record CustomerConfigParams(int x1, int x2) {
}
