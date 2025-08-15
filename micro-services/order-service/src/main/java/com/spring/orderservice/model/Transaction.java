package com.spring.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    private String status;
    private String message;
    private String items;


    public Transaction(String transactionId, String status, String message, String items) {
        this.transactionId = transactionId;
        this.status = status;
        this.message = message;
        this.items = items;
    }
}
