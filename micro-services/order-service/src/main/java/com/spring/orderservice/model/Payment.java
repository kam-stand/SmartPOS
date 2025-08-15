package com.spring.orderservice.model;

public record Payment(
        String email,
        String cardNumber,
        String cardHolderName,
        String expiryMonth,
        String expiryYear,
        String cvv
) {}
