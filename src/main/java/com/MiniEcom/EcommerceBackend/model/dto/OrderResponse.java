package com.MiniEcom.EcommerceBackend.model.dto;

import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
        String orderId,
        String customerName,
        String email,
        String status,
        LocalDate dateDate,
        List<OrderItemRequest> items
) {
}
