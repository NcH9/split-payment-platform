package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String products;
    private Double paidPrice;
    private Double fullPrice;

    @ManyToOne
    @JoinColumn(name = "user_id") // FK
    private User user;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order() {}
}
