package com.spring.paymentservice.service;

import com.spring.paymentservice.model.CreditCard;
import com.spring.paymentservice.model.Payment;
import com.spring.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    public String processPayment(CreditCard creditCard, String transactionId) {
        if (creditCard.cvv().equals("123"))
        {
            return "INVALID CVV";
        }

        if (creditCard.expiryYear().equals("2025") && creditCard.expiryMonth().equals("01"))
        {
            return "INVALID EXPIRY YEAR";
        }

        Payment payment = new Payment();
        payment.setAmount(creditCard.amt());
        payment.setStatus("SUCCESS");
        payment.setPaymentDate(new java.util.Date());
        payment.setTransactionId(transactionId);
        paymentRepository.save(payment);
        return payment.toString();
    }
}
