package com.example.billingservice.controllers;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.feign.CustomerRestClient;
import com.example.billingservice.feign.ProductRestClient;
import com.example.billingservice.models.Customer;
import com.example.billingservice.services.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;
    private final CustomerRestClient customerRestClient;
    private final ProductRestClient productRestClient;
    public BillController(final BillService billService, final CustomerRestClient customerRestClient, final ProductRestClient productRestClient) {
        this.billService = billService;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getBills() {
        List<Bill> bills = billService.findAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        Optional<Bill> bill = billService.findBillById(id);
        Customer customer = customerRestClient.getCustomerById(bill.get().getCustomerId());
        bill.get().setCustomer(customer);
        return bill.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
