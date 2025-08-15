package com.spring.paymentservice.controller;

import com.spring.paymentservice.model.CreditCard;
import com.spring.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("")
    public ResponseEntity<?> processPayment(@RequestBody CreditCard creditCard, @RequestParam("transactionId") String transactionId) {
        return ResponseEntity.ok(paymentService.processPayment(creditCard, transactionId));
    }


    @GetMapping("")
    public ResponseEntity<?> findAllPayments() {
        return ResponseEntity.ok(paymentService.findAll());
    }
}
