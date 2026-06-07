package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class PaymentCreateDto {
    @NotNull
    private Long orderId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double paidPrice;

    public PaymentCreateDto() {}

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Double paidPrice) {
        this.paidPrice = paidPrice;
    }
}
