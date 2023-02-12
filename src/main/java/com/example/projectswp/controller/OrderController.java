package com.example.projectswp.controller;

import com.example.projectswp.model.Order;
import com.example.projectswp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/orders")
@CrossOrigin
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderRepository.getOrders();
        return orders != null? ResponseEntity.ok(orders) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{orderID}")
    public ResponseEntity<Order> getOrder(@PathVariable int orderID) {
        Order order = orderRepository.getOrder(orderID);
        return order != null? ResponseEntity.ok(order) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        boolean result = orderRepository.insertOrder(order);
        URI uri = URI.create("localhost:8080/api/orders/" + orderRepository.getLastOrderID() );
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable int orderId, @RequestBody Order order){
        boolean isUpdated = orderRepository.updateOrder(orderId, order);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Order> deleteOrder(@PathVariable int orderId){
        boolean isDeleted = orderRepository.deleteOrder(orderId);
        return isDeleted ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
