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



    public String GetTransactionProductNames(){
        List<Transaction> transactions = transactionRepository.findAll();

        StringBuilder result = new StringBuilder();

        for (Transaction t : transactions) {
            // Split items string and convert to Long
            List<Long> productIds = Arrays.stream(t.getItems().split(","))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            // Call the product service via Feign client to get product names
            String productNames = productClient.getAllProducts(productIds);

            // Append to the result
            result.append(t.getTransactionId())
                    .append(": ")
                    .append(productNames)
                    .append("\n");
        }

        return result.toString();
    }

}
