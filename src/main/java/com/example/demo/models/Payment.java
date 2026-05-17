package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double paidPrice;

    @OneToOne
    @JoinColumn(name = "order_id") // FK
    private Order order;

    public Payment() {}

    // getters/setters
}
