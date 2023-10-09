package com.viepedialearn2.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id") // Updated column name
    private int productId; // Updated field name and data type

    private int quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "transaction_order_id")
    private Order order;

    // Constructors
    public OrderItem() {
    }

    public OrderItem(int productId, int quantity, Order order) {
        this.productId = productId;
        this.quantity = quantity;
        this.order = order;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() { // Updated getter name
        return productId;
    }

    public void setProductId(int productId) { // Updated setter name
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
