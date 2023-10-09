package com.viepedialearn2.orderservice.repository;

import com.viepedialearn2.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // You can add custom query methods here if needed
}
