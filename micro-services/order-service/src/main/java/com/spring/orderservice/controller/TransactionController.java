package com.spring.orderservice.controller;

import com.spring.orderservice.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping("")
    public ResponseEntity<?> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransactionById(Long transactionId) {
        return ResponseEntity.ok(transactionService.getTransactionById(transactionId));
    }




}
