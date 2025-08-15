package com.spring.orderservice.feign;

import com.spring.orderservice.model.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @PostMapping("/inventory/process-order")
    String checkAndUpdateInventory(@RequestBody Cart cart);
}
