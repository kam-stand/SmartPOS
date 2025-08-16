package com.spring.orderservice.service;

import com.spring.orderservice.feign.InventoryClient;
import com.spring.orderservice.feign.PaymentClient;
import com.spring.orderservice.feign.ProductClient;
import com.spring.orderservice.model.Order;
import com.spring.orderservice.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


@Service
public class OrderService {


    private final InventoryClient inventoryClient;

    private final PaymentClient paymentClient;

    private final TransactionService transactionService;

    private final ProductClient productClient;
    public OrderService(InventoryClient inventoryClient, PaymentClient paymentClient, TransactionService transactionService, ProductClient productClient) {
        this.inventoryClient = inventoryClient;
        this.paymentClient = paymentClient;
        this.transactionService = transactionService;
        this.productClient = productClient;
    }

    public String processOrder(Order order) {
        // Step 1: Check and update inventory
        String transactionId = inventoryClient.checkAndUpdateInventory(order.getCart());
        if (transactionId.startsWith("Order Cannot be processed")) {
            return transactionId;
        }

        // Step 2: Process payment
        String paymentResponse = paymentClient.processPayment(order.getPayment(), transactionId);
        if (paymentResponse.startsWith("INVALID CVV") || paymentResponse.startsWith("INVALID EXPIRY YEAR")) {
            return "Order cannot be processed due to invalid card details";
        }

        // Step 3: Generate random order status
        String randomStatus = List.of("SUCCESS", "PROGRESS", "DELAYED")
                .get(ThreadLocalRandom.current().nextInt(3));

        // Step 4: Build cart items string
        String cartItems = order.getCart()
                .purchaseOrderItems()
                .keySet()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        // Step 5: Save transaction
        Transaction transaction = new Transaction(transactionId, randomStatus, "", cartItems);
        transactionService.saveTransaction(transaction);

        // Step 6: Return final response
        return String.format("Order Processed Successfully. Transaction ID: %s, Payment Info: %s",
                transactionId, paymentResponse);
    }




}
