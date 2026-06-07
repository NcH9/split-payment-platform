package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderCreateDto {
    @NotNull
    private Long userId;

    @NotBlank
    private String products;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private Double paidPrice;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private Double fullPrice;

    public OrderCreateDto() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Double paidPrice) {
        this.paidPrice = paidPrice;
    }

    public Double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Double fullPrice) {
        this.fullPrice = fullPrice;
    }
}
