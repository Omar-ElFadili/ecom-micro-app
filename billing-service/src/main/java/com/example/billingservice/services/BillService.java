package com.example.billingservice.services;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.repositories.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }
    public Optional<Bill> findBillById(long id) {
        return billRepository.findById(id);
    }
    public List<Bill> findAllBills() {
        return billRepository.findAll();
    }
}
