package com.viepedialearn2.orderservice.controller;

import com.viepedialearn2.orderservice.dto.OrderResponse;
import com.viepedialearn2.orderservice.dto.ProductQuantityRequest;
import com.viepedialearn2.orderservice.dto.CreateOrderRequest;
import com.viepedialearn2.orderservice.model.Order;
import com.viepedialearn2.orderservice.model.OrderItem;
import com.viepedialearn2.orderservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public OrderController(OrderRepository orderRepository, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
		this.webClientBuilder = webClientBuilder;
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
    
    @PostMapping("/createOrderAndCheckAvailability")
    public String createOrderAndCheckAvailability(@RequestBody CreateOrderRequest createOrderRequest) {
        // Create the order
        Order order = new Order(createOrderRequest.getOrderNumber(), createOrderRequest.getOrderItems());
//        orderRepository.save(order);

        // Prepare the request to check product availability
        List<ProductQuantityRequest> productQuantities = createProductQuantityRequestList(order.getOrderItems());

        // Make a POST request to the product service synchronously
        Boolean productsAvailable = webClientBuilder
                .build()
                .post()
                .uri("http://product-service/api/product/canBuy")
                .bodyValue(productQuantities)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        		
        if (productsAvailable != null && productsAvailable) {
            return "Order created successfully, and products are available.";
        } else {
            return "Order created, but some products are not available.";
        }
    }

    private List<ProductQuantityRequest> createProductQuantityRequestList(List<OrderItem> orderItems) {
        List<ProductQuantityRequest> productQuantities = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            productQuantities.add(new ProductQuantityRequest(orderItem.getProductId(), orderItem.getQuantity()));
        }
        return productQuantities;
    }
}
