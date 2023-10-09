package com.viepedialearn2.orderservice.repository;

import com.viepedialearn2.orderservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    // You can add custom query methods here if needed
}