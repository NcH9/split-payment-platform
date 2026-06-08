package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.PaymentCreateDto;
import com.example.demo.models.Payment;
import com.example.demo.models.Order;
import com.example.demo.repositories.PaymentRepository;
import com.example.demo.repositories.OrderRepository;

@RestController
@RequestMapping("/api")
public class PaymentController {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentController(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public Payment createPayment(@RequestBody @jakarta.validation.Valid PaymentCreateDto dto) {
        Order order = orderRepository.findById(dto.getOrderId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        if (order.getPaidPrice() > order.getFullPrice() 
            || (dto.getPaidPrice() + order.getPaidPrice())> order.getFullPrice()) 
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paid price cannot be greater than full price");
        }
        
        if (dto.getPaidPrice() > 0) {
            order.setPaidPrice(order.getPaidPrice() + dto.getPaidPrice());
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaidPrice(dto.getPaidPrice());

        orderRepository.save(order);

        return paymentRepository.save(payment);
    }
}
