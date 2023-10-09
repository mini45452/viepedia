package com.viepedialearn2.orderservice.controller;

import com.viepedialearn2.orderservice.dto.OrderResponse;
import com.viepedialearn2.orderservice.model.Order;
import com.viepedialearn2.orderservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}/")
    public ResponseEntity<?> getOrderById(@PathVariable("id") int orderId) {
        // Fetch the order by ID
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            // Create a DTO (Data Transfer Object) to hold the response
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderNumber(order.getOrderNumber());
            orderResponse.setOrderItems(order.getOrderItems());

            return ResponseEntity.ok(orderResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
