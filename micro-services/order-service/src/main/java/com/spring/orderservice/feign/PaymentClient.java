package com.spring.orderservice.feign;

import com.spring.orderservice.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping("/payments")
    String processPayment(@RequestBody Payment payment, @RequestParam("transactionId") String transactionId);
}
