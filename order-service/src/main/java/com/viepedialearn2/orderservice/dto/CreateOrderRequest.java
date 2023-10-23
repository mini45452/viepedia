package com.viepedialearn2.orderservice.dto;

import java.util.List;
import com.viepedialearn2.orderservice.model.OrderItem;

public class CreateOrderRequest {
    private String orderNumber;
    private List<OrderItem> orderItems;

    public CreateOrderRequest() {
        // Default constructor
    }

    public CreateOrderRequest(String orderNumber, List<OrderItem> orderItems) {
        this.orderNumber = orderNumber;
        this.orderItems = orderItems;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
