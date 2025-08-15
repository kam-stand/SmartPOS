package com.spring.inventoryservice.controller;

import com.spring.inventoryservice.model.Inventory;
import com.spring.inventoryservice.model.PurchaseOrder;
import com.spring.inventoryservice.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @GetMapping("")
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    public String getInventoryById(@PathVariable  Long id) {
        return inventoryService.getInventoryById(id).toString();
    }


    @PostMapping("")
    public String saveInventory(@RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory).toString();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInventory(@PathVariable Long id, @RequestParam int Quantity) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, Quantity));
    }


    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }

    // Endpoint to process a purchase order
    @PostMapping("/process-order")
    public ResponseEntity<String> processPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        String result = inventoryService.checkAndUpdateInventory(purchaseOrder);

        if (result.startsWith("Order cannot be processed")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok("Order processed successfully. Transaction ID: " + result);
    }
}
