package com.MiniEcom.EcommerceBackend.Controller;

import com.MiniEcom.EcommerceBackend.Service.OrderService;
import com.MiniEcom.EcommerceBackend.model.dto.OrderRequest;
import com.MiniEcom.EcommerceBackend.model.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/orders/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = service.placeOrder(orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        List<OrderResponse> responses = service.getAllorderResponses();
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

}
