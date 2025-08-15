package com.spring.inventoryservice.service;

import com.spring.inventoryservice.model.Inventory;
import com.spring.inventoryservice.repository.InvetoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Inventory updateInventory(Long id,  Inventory inventory) {
        inventory.setId(id);
        return invetoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        invetoryRepository.deleteById(id);
    }
}
