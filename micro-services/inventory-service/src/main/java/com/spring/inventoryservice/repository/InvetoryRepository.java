package com.spring.inventoryservice.repository;

import com.spring.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvetoryRepository extends JpaRepository<Inventory, Long> {
}
