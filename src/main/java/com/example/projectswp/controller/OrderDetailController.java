package com.example.projectswp.controller;

import com.example.projectswp.model.OrderDetail;
import com.example.projectswp.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/order-details")
public class OrderDetailController {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @GetMapping("")
    public ResponseEntity<List<OrderDetail>> getOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetails();
        return orderDetails != null? ResponseEntity.ok(orderDetails) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{orderDetailID}")
    public ResponseEntity<OrderDetail> getOrderDetail(@PathVariable int orderDetailID) {
        OrderDetail orderDetail = orderDetailRepository.getOrderDetail(orderDetailID);
        return orderDetail != null? ResponseEntity.ok(orderDetail) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("")
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail){
        boolean result = orderDetailRepository.insertOrderDetail(orderDetail);
        URI uri = URI.create("localhost:8080/api/orderDetails/" + orderDetailRepository.getLastOrderDetailID() );
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/{orderDetailId}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable int orderDetailId, @RequestBody OrderDetail orderDetail){
        boolean isUpdated = orderDetailRepository.updateOrderDetail(orderDetailId, orderDetail);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{orderDetailId}")
    public ResponseEntity<OrderDetail> deleteOrderDetail(@PathVariable int orderDetailId){
        boolean isDeleted = orderDetailRepository.deleteOrderDetail(orderDetailId);
        return isDeleted ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
}
