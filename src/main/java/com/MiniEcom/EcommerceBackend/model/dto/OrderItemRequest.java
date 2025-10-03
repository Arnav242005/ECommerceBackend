package com.MiniEcom.EcommerceBackend.model.dto;

public record OrderItemRequest(
        int productId,
        int quantity
){}
