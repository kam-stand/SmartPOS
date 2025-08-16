package com.spring.orderservice.service;

import com.spring.orderservice.feign.ProductClient;
import com.spring.orderservice.model.Transaction;
import com.spring.orderservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final ProductClient productClient;

    public TransactionService(TransactionRepository transactionRepository, ProductClient productClient) {
        this.transactionRepository = transactionRepository;
        this.productClient = productClient;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

   public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId).orElse(null);
   }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);

    }


}
