package com.spring.paymentservice.model;

public record CreditCard(
        String email,
        String cardNumber,
        String cardHolderName,
        String expiryMonth,
        String expiryYear,
        String cvv,
        double amt

) {
}
