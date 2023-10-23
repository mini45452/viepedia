package com.viepedialearn2.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
//        return WebClient.builder().baseUrl("http://localhost:8081"); // Adjust the base URL as needed
        return WebClient.builder(); // Adjust the base URL as needed
    }
}