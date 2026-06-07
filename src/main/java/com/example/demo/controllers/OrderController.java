package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.OrderCreateDto;
import com.example.demo.models.Order;
import com.example.demo.models.User;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class OrderController extends BaseController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody @jakarta.validation.Valid OrderCreateDto dto) {
        User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setPaidPrice(dto.getPaidPrice());
        order.setFullPrice(dto.getFullPrice());

        return orderRepository.save(order);
    }
}
