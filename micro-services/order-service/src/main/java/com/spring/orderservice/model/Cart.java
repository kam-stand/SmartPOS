package com.spring.orderservice.model;

import java.util.HashMap;

public record Cart(
        HashMap<Long, Integer> items
) {
}
