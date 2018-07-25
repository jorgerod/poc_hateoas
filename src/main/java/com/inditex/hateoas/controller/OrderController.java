package com.inditex.hateoas.controller;

import com.inditex.hateoas.dao.OrderRepository;
import com.inditex.hateoas.model.Order;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/orders/", produces = "application/hal+json")
public class OrderController {

    private final OrderRepository orderRepository;

    @GetMapping("{orderId}")
    public ResponseEntity<Order> getOrderById(Integer orderId) {
        return ResponseEntity.ok(orderRepository.getOne(orderId));
    }

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
