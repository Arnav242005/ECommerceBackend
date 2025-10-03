package com.MiniEcom.EcommerceBackend.Service;

import com.MiniEcom.EcommerceBackend.Repository.OrderRepo;
import com.MiniEcom.EcommerceBackend.model.dto.OrderRequest;
import com.MiniEcom.EcommerceBackend.model.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo repo;

    public OrderResponse placeOrder(OrderRequest orderRequest) {

    }

    public List<OrderResponse> getAllorderResponses() {
        return null;
    }
}
