package com.spring.orderservice.controller;


import com.spring.orderservice.model.Order;
import com.spring.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("")
    public String createOrder(@RequestBody Order order) {
        return orderService.processOrder(order);
    }

    @GetMapping("")
    public String getAllOrders() {
        return "All Orders";
    }

}
