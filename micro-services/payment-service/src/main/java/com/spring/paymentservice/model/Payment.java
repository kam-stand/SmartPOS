package com.spring.paymentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public Date paymentDate;
    private String status;
    private String transactionId;
    double amount;

    public Payment(Date paymentDate, String status, String transactionId, double amount) {
        this.paymentDate = paymentDate;
        this.status = status;
        this.transactionId = transactionId;
        this.amount = amount;
    }

}
