package com.MiniEcom.EcommerceBackend.Service;

import com.MiniEcom.EcommerceBackend.Repository.OrderRepo;
import com.MiniEcom.EcommerceBackend.model.Order;
import com.MiniEcom.EcommerceBackend.model.dto.OrderRequest;
import com.MiniEcom.EcommerceBackend.model.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepo repo;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        String orderID = UUID.randomUUID().toString().substring(0,8).toUpperCase();
        order.setOrderId(orderID);
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.email());
        order.setOrderDate(LocalDate.now());
        order.setStatus("PLACED");
        return null;
    }

    public List<OrderResponse> getAllorderResponses() {
        return null;
    }
}
