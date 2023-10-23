package com.viepedialearn2.orderservice.dto;

public class ProductQuantityRequest {
    private int productId;
    private int requiredAmount;

    public ProductQuantityRequest() {
        // Default constructor
    }

    public ProductQuantityRequest(int productId, int requiredAmount) {
        this.productId = productId;
        this.requiredAmount = requiredAmount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }
}
