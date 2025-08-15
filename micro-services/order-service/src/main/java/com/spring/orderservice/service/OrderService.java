package com.spring.orderservice.service;

import com.spring.orderservice.feign.InventoryClient;
import com.spring.orderservice.model.Order;
import org.springframework.stereotype.Service;


@Service
public class OrderService {


    private final InventoryClient inventoryClient;

    public OrderService(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }


    public String processOrder(Order order) {

        String transaction_id = inventoryClient.checkAndUpdateInventory(order.getCart());

        if (transaction_id.startsWith("Order Cannot be processed")) {

            return transaction_id;
        }

        return "Order Processed Successfully";
    }
}
