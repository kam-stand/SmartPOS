package com.spring.orderservice.controller;


import com.spring.orderservice.model.Order;
import com.spring.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.processOrder(order));
    }



}
