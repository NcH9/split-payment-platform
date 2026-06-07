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

    public Long getId() {
        return id;
    }

    public Double getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Double paidPrice) {
        this.paidPrice = paidPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
