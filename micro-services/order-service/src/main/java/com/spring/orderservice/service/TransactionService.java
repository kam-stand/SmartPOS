package com.spring.orderservice.service;

import com.spring.orderservice.model.Transaction;
import com.spring.orderservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction getTransaction(String transactionId) {
        return transactionRepository.findById(transactionId).orElse(null);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void deleteTransaction(String transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);

    }

}
