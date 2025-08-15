package com.spring.inventoryservice.service;

import com.spring.inventoryservice.model.Inventory;
import com.spring.inventoryservice.model.PurchaseOrder;
import com.spring.inventoryservice.repository.InvetoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class InventoryService {

    private final InvetoryRepository invetoryRepository;

    public InventoryService(InvetoryRepository invetoryRepository) {
        this.invetoryRepository = invetoryRepository;
    }


    public List<Inventory> getAllInventory() {
        return invetoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return invetoryRepository.findById(id).orElse(null);
    }

    public Inventory saveInventory(Inventory inventory) {
        return invetoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long id, int Quantity) {
        Inventory inventory = invetoryRepository.findById(id).orElse(null);
        if (inventory != null) {
            inventory.setQuantity(inventory.getQuantity() - Quantity);
            return invetoryRepository.save(inventory);
        }
        return null;
    }

    public void deleteInventory(Long id) {
        invetoryRepository.deleteById(id);
    }


    public String checkAndUpdateInventory(PurchaseOrder purchaseOrder) {
        // First, check if all products have enough quantity
        for (Map.Entry<Long, Integer> entry : purchaseOrder.purchaseOrderItems().entrySet()) {
            Long productId = entry.getKey();
            Integer orderQuantity = entry.getValue();

            Inventory inventory = invetoryRepository.findById(productId).orElse(null);

            if (inventory == null) {
                return "Order cannot be processed: Product ID " + productId + " not found.";
            }

            if (inventory.getQuantity() < orderQuantity) {
                return "Order cannot be processed: Not enough quantity for Product ID " + productId;
            }
        }

        // All products have enough quantity, so update the inventory
        for (Map.Entry<Long, Integer> entry : purchaseOrder.purchaseOrderItems().entrySet()) {
            Long productId = entry.getKey();
            Integer orderQuantity = entry.getValue();

            Inventory inventory = invetoryRepository.findById(productId).get();
            inventory.setQuantity(inventory.getQuantity() - orderQuantity);
            invetoryRepository.save(inventory);
        }

        // Generate and return a transaction ID
        return UUID.randomUUID().toString();
    }

}
