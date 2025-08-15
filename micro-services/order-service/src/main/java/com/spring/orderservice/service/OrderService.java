package com.spring.orderservice.service;

import com.spring.orderservice.feign.InventoryClient;
import com.spring.orderservice.feign.PaymentClient;
import com.spring.orderservice.feign.ProductClient;
import com.spring.orderservice.model.Order;
import com.spring.orderservice.model.Transaction;
import org.springframework.stereotype.Service;


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

        String transactionId = inventoryClient.checkAndUpdateInventory(order.getCart());

        if (transactionId.startsWith("Order Cannot be processed")) {

            return transactionId;
        }

        String payment = paymentClient.processPayment(order.getPayment(), transactionId);
        if (payment.startsWith("INVALID CVV") || payment.startsWith("INVALID EXPIRY YEAR")) {

            return "Order cannot be processed due to invalid card details";
        }

        transactionService.saveTransaction(new Transaction(transactionId, "SUCCESS", "", order.getCart().purchaseOrderItems().keySet().toString()));

        return "Order Processed Successfully " + transactionId  + "Payment info: " +  payment;
    }
}
