package com.spring.inventoryservice.model;

import java.util.HashMap;

public record PurchaseOrder(
        HashMap<Long, Integer> purchaseOrderItems

) {
}
