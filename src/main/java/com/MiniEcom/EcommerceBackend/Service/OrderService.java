package com.MiniEcom.EcommerceBackend.Service;

import com.MiniEcom.EcommerceBackend.Repository.OrderRepo;
import com.MiniEcom.EcommerceBackend.Repository.ProductRepo;
import com.MiniEcom.EcommerceBackend.model.Order;
import com.MiniEcom.EcommerceBackend.model.OrderItem;
import com.MiniEcom.EcommerceBackend.model.Product;
import com.MiniEcom.EcommerceBackend.model.dto.OrderItemRequest;
import com.MiniEcom.EcommerceBackend.model.dto.OrderItemResponse;
import com.MiniEcom.EcommerceBackend.model.dto.OrderRequest;
import com.MiniEcom.EcommerceBackend.model.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private ProductRepo prodrepo;

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

        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequest itemReq: orderRequest.items()){
            Product product = prodrepo.findById(itemReq.productId())
                    .orElseThrow(() -> new RuntimeException("Product Not Found"));
            product.setStockQuantity(product.getStockQuantity() - itemReq.quantity());
            prodrepo.save(product);

            OrderItem orderItem = OrderItem
                    .builder()
                    .product(product)
                    .quantity(itemReq.quantity())
                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(itemReq.quantity())))
                    .order(order)
                    .build();

            orderItems.add(orderItem);

        }

        order.setOrderItems(orderItems);
        Order savedOrder = repo.save(order);

        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for(OrderItem item : order.getOrderItems()){
            OrderItemResponse orderItemResponse = new OrderItemResponse(
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotalPrice()
            );
        }

        OrderResponse orderResponse = new OrderResponse(savedOrder.getOrderId(),
                savedOrder.getCustomerName(),
                savedOrder.getEmail(),
                savedOrder.getStatus(),
                savedOrder.getOrderDate(),
                itemResponses);

        return null;
    }

    public List<OrderResponse> getAllorderResponses() {
       List<Order> orders = repo.findAll();
       List<OrderResponse> orderResponses = new ArrayList<>();

       for(Order order : orders){

           List<OrderItemResponse> itemResponses = new ArrayList<>();

           for(OrderItem item: order.getOrderItems()){
               OrderItemResponse orderItemResponse = new OrderItemResponse(
                       item.getProduct().getName(),
                       item.getQuantity(),
                       item.getTotalPrice()
               );
               itemResponses.add(orderItemResponse);
           }

           OrderResponse orderResponse = new OrderResponse(
                   order.getOrderId(),
                   order.getCustomerName(),
                   order.getEmail(),
                   order.getStatus(),
                   order.getOrderDate(),
                   itemResponses
           );
           orderResponses.add(orderResponse);
       }
       return orderResponses;
    }
}
